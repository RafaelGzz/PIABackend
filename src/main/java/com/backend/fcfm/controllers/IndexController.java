package com.backend.fcfm.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.backend.fcfm.entitys.Cliente;
import com.backend.fcfm.models.dao.ClienteDao;

@Controller
@SessionAttributes("usuario")
public class IndexController {
	
	@Autowired
	private ClienteDao clienteDao;

	@GetMapping({ "/", "/login" })
	public String login(Model model) {
		
		if(model.getAttribute("usuario") != null) {
			return "redirect:/index";
		}
		
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
		
		Cliente cliente = clienteDao.login(user, password);
		model.addAttribute("usuario", cliente);
		
		if(cliente == null) {
			model.addAttribute("error", "Cliente no existe");
			sesion.setComplete();
			return "login";
		}

		return "redirect:/index";
	}
	
	@GetMapping({"/index"})
	public String index(Model model) {
		
		if(model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		return "index";
	}
	
	@GetMapping({"/logOut"})
	public String logOut(Model model, SessionStatus sesion) {
		sesion.setComplete();
		return "redirect:/login";
	}
	
	@GetMapping({ "/registro" })
	public String registro(Model model) {
		
		if(model.getAttribute("usuario") != null) {
			return "redirect:/index";
		}
		Cliente cliente = new Cliente();
		cliente.setTelefono(0l);
		cliente.setMonto(0f);
		model.addAttribute("cliente", cliente);
		return "registro";
	}
	
	@PostMapping({"/registrar"})
	public String registrar(@Valid Cliente cliente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "registro";
		}
		clienteDao.insert(cliente);
		
		return "login";
	}

}
