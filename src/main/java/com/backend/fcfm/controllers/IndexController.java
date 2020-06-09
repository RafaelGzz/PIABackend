package com.backend.fcfm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.backend.fcfm.entitys.Cliente;
import com.backend.fcfm.models.dao.ClienteDao;

@Controller
@SessionAttributes("user")
public class IndexController {
	
	@Autowired
	private ClienteDao clienteDao;

	@GetMapping({ "/", "/login" })
	public String login(Model model) {
		String user = new String();
		String password = new String();
		model.addAttribute("user", user);
		model.addAttribute("password", password);
		return "login";
	}

	@PostMapping({ "/login/ingresar" })
	public String ingresar(@RequestParam("user") String user, @RequestParam("password") String password, Model model, SessionStatus sesion) {
		if(user == null || user == "" || password == null || password == "") {
			model.addAttribute("user", user);
			model.addAttribute("password", password);
			model.addAttribute("error", "Completa todos los campos");
			return "login";
		}
		
		Cliente cliente = clienteDao.login(user.toUpperCase(), password);
		if(cliente == null) {
			model.addAttribute("error", "Cliente no existe");
			sesion.setComplete();
			return "login";
		}

		return "index";
	}

}
