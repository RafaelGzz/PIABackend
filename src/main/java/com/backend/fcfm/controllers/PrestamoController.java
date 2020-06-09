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

import com.backend.fcfm.entitys.Prestamo;
import com.backend.fcfm.models.dao.PrestamoDao;

@Controller
@RequestMapping(path = "/prestamo")
@SessionAttributes("prestamo")
public class PrestamoController {

	@Autowired
	private PrestamoDao prestamoDao;

	@GetMapping({ "", "/" })
	public String Prestamos(Model model) {
		model.addAttribute("titulo", "Prestamo");
	    model.addAttribute("Prestamos",prestamoDao.findAll());

		return "catalogo/prestamo/lista";
	}

	@GetMapping({ "/form" })
	public String form(Model model) {
		Prestamo prestamo = new Prestamo();
		model.addAttribute("prestamo", prestamo);
		return "catalogo/prestamo/form";
	}

	@PostMapping({ "/guardar" })
	public String guardar(@Valid Prestamo prestamo, BindingResult result, Model model, SessionStatus sesion) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Prestamo");
			return "catalogo/prestamo/form";
		}
		prestamoDao.insert(prestamo);
		sesion.setComplete();
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
