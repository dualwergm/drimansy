package com.dg.drimansy.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dg.drimansy.model.Product;
import com.dg.drimansy.service.ProductService;
import com.dg.drimansy.view.vo.ProductVO;
import com.dg.drimansy.view.web.AjaxResponseBody;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public ModelAndView getProducts() {
		ModelAndView mav = new ModelAndView();
		List<Product> findAll = productService.findAllOrdered();
		mav.addObject("products", productService.getProductsVO(findAll));
		mav.setViewName("basic/product");
		return mav;
	}

	@GetMapping("/response")
	@ResponseBody
	public AjaxResponseBody getAjaxResponse() {
		AjaxResponseBody response = new AjaxResponseBody();
		try {
			List<Product> findAll = productService.findAllOrdered();
			List<ProductVO> productsVO = productService.getProductsVO(findAll);
			response.setList(productsVO);
			response.setStatus(HttpStatus.OK);
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
