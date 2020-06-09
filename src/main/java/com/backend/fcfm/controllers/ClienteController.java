package com.backend.fcfm.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.backend.fcfm.entitys.Cliente;
import com.backend.fcfm.models.dao.ClienteDao;

@Controller
@RequestMapping(path = "/cliente")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private ClienteDao clienteDao;

	@GetMapping({ "", "/" })
	public String Clientes(Model model) {
		model.addAttribute("titulo", "Cliente");
	    model.addAttribute("clientes",clienteDao.findAll());

		return "catalogo/cliente/lista";
	}

	@GetMapping({ "/form" })
	public String form(Model model) {
		Cliente cliente = new Cliente();
		cliente.setTelefono(0l);
		model.addAttribute("cliente", cliente);
		return "catalogo/cliente/form";
	}

	@PostMapping({ "/guardar" })
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus sesion) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Cliente");
			return "catalogo/cliente/form";
		}
		clienteDao.insert(cliente);
		return "redirect:/cliente";
	}
	
	@GetMapping({ "/form/{id}" })
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("titulo", "Alumno");
		Cliente editar = clienteDao.find(id);
		model.addAttribute("cliente", editar);
		return "catalogo/cliente/form";
		
	}

	@GetMapping({ "/eliminar/{id}" })
	public String eliminar(@PathVariable Integer id, Model model) {
		if (id != null && id > 0) {
			clienteDao.delete(id);
		}
		return "redirect:/cliente";
	}

}
