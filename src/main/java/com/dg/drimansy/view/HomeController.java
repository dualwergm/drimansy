package com.dg.drimansy.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dg.drimansy.service.UserService;

@RestController
@RequestMapping("app")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public ModelAndView getBooks() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", userService.getUserId());
		mav.setViewName("/home/home");
		return mav;
	}
}
