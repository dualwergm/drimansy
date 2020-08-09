package com.dg.drimansy.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("app")
public class LoginController {
	@GetMapping(value= {"/login", "/"})
	public ModelAndView login() {
		    ModelAndView mav = new ModelAndView();
		    mav.addObject("errorMsg", "Usuario o contraseña incorrecta!");
		    mav.setViewName("/login/login");
		    return mav;
    }
}
