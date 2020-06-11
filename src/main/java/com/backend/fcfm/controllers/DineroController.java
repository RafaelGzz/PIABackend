package com.backend.fcfm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.backend.fcfm.entitys.Cliente;
import com.backend.fcfm.models.dao.ClienteDao;

@Controller
@SessionAttributes("usuario")
public class DineroController{
	@Autowired
	ClienteDao clienteDao; 
	
	@GetMapping({ "/dinero" })
	public String form(Model model) {
		if(model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		Float sumaTotal;
		Cliente cliente = new Cliente();
		cliente = clienteDao.mayCliente();
		System.out.println(cliente);
		sumaTotal = clienteDao.totalMoney();
		model.addAttribute("cliente", cliente);
		model.addAttribute("sumaTotal", sumaTotal);
		return "catalogo/Dinero/dinero";
	}	
	
	
}
