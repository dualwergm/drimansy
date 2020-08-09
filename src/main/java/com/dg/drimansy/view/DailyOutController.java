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

import com.dg.drimansy.model.DailyOut;
import com.dg.drimansy.model.Product;
import com.dg.drimansy.service.CarService;
import com.dg.drimansy.service.CollaboratorService;
import com.dg.drimansy.service.DailyOutService;
import com.dg.drimansy.service.ProductService;
import com.dg.drimansy.utils.Utils;
import com.dg.drimansy.view.web.AjaxResponseBody;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("daily")
public class DailyOutController {
	@Autowired
	private DailyOutService dailyOutService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CarService carService;
	@Autowired
	private CollaboratorService collaboratorService;

	@GetMapping("/list")
	public ModelAndView find() {
		ModelAndView mav = new ModelAndView();
		List<DailyOut> dailyList = dailyOutService.findAllOrdered();
		mav.addObject("dailys", dailyOutService.getDailyOutsVO(dailyList));
		mav.setViewName("daily/list");
		return mav;
	}

	@GetMapping("/out")
	public ModelAndView getOut(@RequestParam String jparams) {
		ModelAndView mav = new ModelAndView();
		JsonNode root = Utils.getJNodeParams(jparams);
		long carId = root.path("carId").asLong();
		DailyOut dailyOut = dailyOutService.findByCarAndDate(carId, new java.util.Date());
		loadOutMAV(mav, dailyOut);
		return mav;
	}

	@GetMapping("/out/{id}")
	public ModelAndView getOutById(@PathVariable long id, String isArrive) {
		ModelAndView mav = new ModelAndView();
		DailyOut dailyOut = dailyOutService.findById(id);
		loadOutMAV(mav, dailyOut);
		mav.addObject("isArrive", Integer.parseInt(isArrive));
		return mav;
	}
	
	private void loadOutMAV(ModelAndView mav, DailyOut dailyOut) {
		List<Product> products = productService.findAllOrdered();
		mav.addObject("cars", carService.getCarsVO());
		mav.addObject("collaborators", collaboratorService.getCollaboratorsVO());
		mav.addObject("dailyOut", dailyOutService.getDailyOut(dailyOut, products));
		mav.setViewName("daily/out");
	}
	
	@PostMapping("/save")
	public AjaxResponseBody save(@RequestBody String jParams) throws ParseException {
		AjaxResponseBody response = new AjaxResponseBody();
		try {
			JsonNode root = Utils.getJNodeParams(Utils.decode(jParams));
			DailyOut out = dailyOutService.getDetailFromWeb(root);
			dailyOutService.save(out);
			response.setStatus(HttpStatus.OK);
			return response;
		} catch (UnsupportedEncodingException e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
}
