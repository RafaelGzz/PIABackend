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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.backend.fcfm.entitys.Cliente;
import com.backend.fcfm.entitys.Prestamo;
import com.backend.fcfm.models.dao.ClienteDao;
import com.backend.fcfm.models.dao.PrestamoDao;

@Controller
@RequestMapping(path = "/prestamo")
@SessionAttributes("prestamo")
public class PrestamoController {

	@Autowired
	private PrestamoDao prestamoDao;

	@Autowired
	private ClienteDao clienteDao;

	@GetMapping({ "", "/" })
	public String Prestamos(Model model) {
		model.addAttribute("titulo", "Prestamos");
		model.addAttribute("prestamos", prestamoDao.findAll());
		return "catalogo/prestamo/lista";
	}

	@GetMapping({ "/form" })
	public String form(Model model) {
		Prestamo prestamo = new Prestamo();
		Cliente cliente = new Cliente();
		prestamo.setCliente(cliente);
		model.addAttribute("prestamo", prestamo);
		return "catalogo/prestamo/form";
	}

	@PostMapping({ "/guardar" })
	public String guardar(@RequestParam(name = "idCliente") Integer idCliente, @Valid Prestamo prestamo,
			BindingResult result, Model model, SessionStatus sesion) {
		
		prestamo.setCliente(clienteDao.find(idCliente));
		System.out.print(idCliente);
		
		if (result.hasErrors() || prestamo.getCliente() == null) {
			model.addAttribute("titulo", "Prestamos");
			return "catalogo/prestamo/form";
		}
		
		prestamoDao.insert(prestamo);
		return "redirect:/prestamo";
	}

	@GetMapping({ "/form/{id}" })
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("titulo", "Alumno");
		Prestamo editar = prestamoDao.find(id);
		model.addAttribute("prestamo", editar);
		return "catalogo/prestamo/form";
	}

	@GetMapping({ "/eliminar/{id}" })
	public String eliminar(@PathVariable Integer id, Model model) {
		if (id != null && id > 0) {
			prestamoDao.delete(id);
		}
		return "redirect:/prestamo";
	}

}
