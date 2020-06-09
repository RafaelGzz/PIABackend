package com.backend.fcfm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/services")
public class ServiceController {
	
	@GetMapping({"/abono"})
	public String abonar() {
		
		return "abono";
	}
	
	@GetMapping({"/prestamo"})
	public String prestamo() {
		return "prestamo";
	}

}
