package com.backend.fcfm.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.backend.fcfm.entitys.Prestamo;

@RequestMapping(path = "/services")
@SessionAttributes("cliente")
public class ServiceController {
	
	@GetMapping({"/abono"})
	public String abonar() {
		
		return "abono";
	}
	
	@GetMapping({"/prestamo"})
	public String prestamo(Model model) {
		Prestamo prestamo = new Prestamo();
		model.addAttribute("prestamo", prestamo);
		return "prestamo";
	}

}
