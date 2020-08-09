package com.dg.drimansy.view;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dg.drimansy.model.Invoice;
import com.dg.drimansy.model.InvoicePayment;
import com.dg.drimansy.service.ClientService;
import com.dg.drimansy.service.CollaboratorService;
import com.dg.drimansy.service.InvoicePaymentService;
import com.dg.drimansy.service.InvoiceService;
import com.dg.drimansy.utils.Utils;
import com.dg.drimansy.view.vo.InvoiceVO;
import com.dg.drimansy.view.web.AjaxResponseBody;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	private InvoicePaymentService paymentService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private CollaboratorService collaboratorService;
	@Autowired
	private ClientService clientService;
	

	@GetMapping("/list")
	public ModelAndView find(@RequestParam(defaultValue = "0") String isModal) {
		ModelAndView mav = new ModelAndView();
		List<InvoicePayment> payments = paymentService.findAllOrdered();
		mav.addObject("collaborators", collaboratorService.getCollaboratorsVO());
		mav.addObject("payments", paymentService.getPaymentsVO(payments));
		mav.setViewName("payment/payments");
		mav.addObject("isModal", Integer.valueOf(isModal));
		return mav;
	}

	@GetMapping("/create")
	public ModelAndView getNew() {
		ModelAndView mav = new ModelAndView();
		loadEditMAV(mav, invoiceService.getInvoiceVO(new Invoice()));
		return mav;
	}	
	
	@GetMapping("/edit/{id}")
	public ModelAndView getById(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		Invoice invoice = invoiceService.findById(id);
		loadEditMAV(mav, invoiceService.getInvoiceVO(invoice));
		return mav;
	}
	
	private void loadEditMAV(ModelAndView mav, InvoiceVO invoiceVO) {
		mav.addObject("collaborators", collaboratorService.getCollaboratorsVO());
		mav.addObject("clients", clientService.getClientsVO());
		mav.addObject("invoiceVO", invoiceVO);
		mav.setViewName("invoice/edit");
	}
	
	@PostMapping("/save")
	public AjaxResponseBody save(@RequestBody String jParams) throws ParseException {
		AjaxResponseBody response = new AjaxResponseBody();
		try {
			JsonNode root = Utils.getJNodeParams(Utils.decode(jParams));
			Invoice invoice = invoiceService.getInvoiceFromWeb(root);
			invoiceService.save(invoice);
			response.setStatus(HttpStatus.OK);
			return response;
		} catch (UnsupportedEncodingException e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
}
