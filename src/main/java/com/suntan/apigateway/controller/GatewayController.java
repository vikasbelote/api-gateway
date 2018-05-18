package com.suntan.apigateway.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GatewayController {
	
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
	
	@RequestMapping("/good-beers")
    public Collection<Beer> goodBeers() {
       
		List<Beer> list = new ArrayList<Beer>();
		
		Beer b1 = new Beer();
		b1.setId(1L);
		b1.setName("ABC");
		
		Beer b2 = new Beer();
		b2.setId(2L);
		b2.setName("PQR");
		
		Beer b3 = new Beer();
		b3.setId(3L);
		b3.setName("XYZ");
		
		list.add(b1);
		list.add(b2);
		list.add(b3);
		
		return list;
    }
	
}

class Beer {
	private Long id;
    private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
