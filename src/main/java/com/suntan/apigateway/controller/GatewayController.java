package com.suntan.apigateway.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.suntan.apigateway.model.UserModel;
import com.suntan.apigateway.repository.UserRepository;





@RestController
public class GatewayController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("index.html");
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(Principal principal) {
		Assert.notNull(principal.getName(), "User is not authenticated.");
		return new ModelAndView("dashboard.html");
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, null, null);
        return "redirect:/";
	}
	
	@RequestMapping("/user")
    public UserModel user(Principal principal) {
		return userRepository.findByUserName(principal.getName());
    }
}


