package com.dg.drimansy.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.Invoice;
import com.dg.drimansy.model.InvoiceDetail;
import com.dg.drimansy.repository.IInvoicePaymentRepository;
import com.dg.drimansy.repository.IInvoiceRepository;
import com.dg.drimansy.utils.Utils;
import com.dg.drimansy.view.vo.InvoiceDetailVO;
import com.dg.drimansy.view.vo.InvoiceVO;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class InvoiceService {
	@Autowired
	private IInvoicePaymentRepository paymentRepository;
	private final IInvoiceRepository invoiceRepository;

	public InvoiceService(IInvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@Transactional(readOnly = true)
	public List<Invoice> findAllOrdered() {
		return this.invoiceRepository.findAllOrdered();
	}

	public List<InvoiceVO> getInvoicesVO(List<Invoice> invoices) {
		List<InvoiceVO> list = new ArrayList<InvoiceVO>();
		for (Invoice invoice: invoices) {
			InvoiceVO invoiceVO = new InvoiceVO();
			invoiceVO.toVO(invoice);
			invoiceVO.setPaymentTotal(paymentRepository.sumPayments(invoice.getId()));
			list.add(invoiceVO);
		}
		return list;
	}

	public Invoice getInvoiceFromWeb(JsonNode root) throws ParseException {
		JsonNode jMaster = (JsonNode)root.get("jMaster");
		Invoice invoice = new Invoice();
		invoice.toEntity(jMaster);
		JsonNode jDetails = (JsonNode)root.get("jDetail");
		for(JsonNode jDetail: jDetails) {
			InvoiceDetail detail = new InvoiceDetail();
			detail.toEntity(jDetail);
			detail.setInvoice(invoice);
			invoice.getDetails().add(detail);
		}
		return invoice;
	}
	
	public void save(Invoice out) {
		this.invoiceRepository.saveAndFlush(out);
	}
	
	@Transactional(readOnly = true)
	public Invoice findById(long invoiceId) {
		Optional<Invoice> findById = this.invoiceRepository.findById(Long.valueOf(invoiceId));
		return findById.isPresent() ? findById.get() : new Invoice();
	}
	
	public InvoiceVO getInvoiceVO(Invoice invoice) {
		InvoiceVO invoiceVO = new InvoiceVO();
		invoiceVO.toVO(invoice);
		if(Utils.gtZero(invoiceVO.getId())) {
			invoiceVO.setPaymentTotal(paymentRepository.sumPayments(invoiceVO.getId()));
		}
		for (InvoiceDetail detail: invoice.getDetails()) {
			InvoiceDetailVO detailVO = new InvoiceDetailVO();
			detailVO.toVO(detail);
			invoiceVO.getDetails().add(detailVO);
		}
		return invoiceVO;
	}
	
}
