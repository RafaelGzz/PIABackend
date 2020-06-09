package com.backend.fcfm.controllers;

import java.util.HashMap;
import java.util.Map;

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
			BindingResult result, Model model) {

		prestamo.setCliente(clienteDao.find(idCliente));

		if (result.hasErrors() || prestamo.getCliente() == null) {
			model.addAttribute("titulo", "Prestamos");
			return "catalogo/prestamo/form";
		}

		prestamoDao.insert(prestamo);
		return "redirect:/prestamo";
	}

	@PostMapping({ "/abonar" })
	public String abonar(@RequestParam(name = "cantidad") Long cantidad, @Valid Prestamo prestamo, Model model) {
		Long total = prestamo.getAbonoTotal() + cantidad;
		if (total > prestamo.getMonto()) {
			Map<String, String> errores = new HashMap<>();
			errores.put("cantidad", "Cantidad excesiva");
			model.addAttribute("errores", errores);
			return "catalogo/prestamo/abono/" + prestamo.getIdPrestamo();
		} else if (total == prestamo.getMonto()) {
			prestamo.setPagado(1);
		}
		prestamo.setAbonoTotal(total);
		prestamoDao.update(prestamo);
		return "redirect:/prestamo";
	}

	@GetMapping({ "/abono/{id}" })
	public String editar(@PathVariable Integer id, Model model) {
		Map<String, String> errores = new HashMap<>();;
		model.addAttribute("errores", errores);
		Prestamo editar = prestamoDao.find(id);
		model.addAttribute("prestamo", editar);
		return "catalogo/prestamo/abono";
	}

}
