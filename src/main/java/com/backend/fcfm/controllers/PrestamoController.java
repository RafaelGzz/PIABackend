package com.backend.fcfm.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@SessionAttributes("usuario")
public class PrestamoController {

	@Autowired
	private PrestamoDao prestamoDao;

	@Autowired
	private ClienteDao clienteDao;

	@GetMapping({ "", "/" })
	public String Prestamos(Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null) {
			return "redirect:/login";
		}
		model.addAttribute("titulo", "Prestamos");
		List<Prestamo> prestamos;
		if (usuario.getUser().equals("Admin")) {
			prestamos = prestamoDao.findAll();
		}else {
			prestamos = prestamoDao.findByClient(usuario.getIdCliente());
		}
		model.addAttribute("prestamos", prestamos);
		return "catalogo/prestamo/lista";
	}

	@GetMapping({ "/buscarId" })
	public String buscarId(@RequestParam(name = "id") Integer id, Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		if (id != null && id >= 0) {
			model.addAttribute("titulo", "Prestamos");
			List<Prestamo> prestamos = new ArrayList<>();
			Prestamo prestamo = prestamoDao.find(id);
			if (prestamo != null) {
				prestamos.add(prestamo);
			}
			model.addAttribute("prestamos", prestamos);
			return "catalogo/prestamo/lista";
		}
		return "redirect:/prestamo";
	}

	@GetMapping({ "/buscarPagados/{opc}" })
	public String buscarPagados(@PathVariable Integer opc, Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		model.addAttribute("titulo", "Prestamos");
		List<Prestamo> prestamos = prestamoDao.findPorPagado(opc);
		model.addAttribute("prestamos", prestamos);
		return "catalogo/prestamo/lista";
	}

	@GetMapping({ "/buscarFecha" })
	public String buscarFecha(@RequestParam(name = "fechaInicio") String fechaInicio,
			@RequestParam(name = "fechaFin") String fechaFin, Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		if (fechaInicio != null && fechaInicio != "" && fechaFin != null && fechaFin != "") {
			List<Prestamo> prestamos = prestamoDao.findFecha(fechaInicio, fechaFin);
			model.addAttribute("prestamos", prestamos);
			model.addAttribute("titulo", "Prestamos");
			return "catalogo/prestamo/lista";
		}
		return "redirect:/prestamo";

	}

	@GetMapping({ "/form" })
	public String form(Model model) {
		if (model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		String mensaje = null;
		model.addAttribute("mensaje", mensaje);
		Prestamo prestamo = new Prestamo();
		Cliente cliente = new Cliente();
		prestamo.setCliente(cliente);
		model.addAttribute("prestamo", prestamo);
		return "catalogo/prestamo/form";
	}

	@PostMapping({ "/guardar" })
	public String guardar(@RequestParam(name = "idCliente") Integer idCliente, @Valid Prestamo prestamo,
			BindingResult result, Model model) {
		if (model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		String mensaje = null;
		if (idCliente != null && idCliente >= 0) {
			prestamo.setCliente(clienteDao.find(idCliente));
			if (prestamo.getCliente() == null) {
				idCliente = null;
				mensaje = "Cliente no existe";
			}
		} else {
			idCliente = null;
			mensaje = "Ingresa un id valido";
		}

		if (result.hasErrors() || prestamo.getCliente() == null || idCliente == null) {
			model.addAttribute("titulo", "Prestamos");
			model.addAttribute("mensaje", mensaje);
			return "catalogo/prestamo/form";
		}

		prestamoDao.insert(prestamo);
		return "redirect:/prestamo";
	}

	@PostMapping({ "/abonar" })
	public String abonar(@RequestParam(name = "cantidad") Long cantidad, @Valid Prestamo prestamo, Model model) {
		if (model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		Long total;
		if (cantidad != null && cantidad >= 0) {
			total = prestamo.getAbonoTotal() + cantidad;
			if (total > prestamo.getMonto()) {
				Map<String, String> errores = new HashMap<>();
				errores.put("cantidad", "Cantidad excesiva");
				model.addAttribute("errores", errores);
				return "catalogo/prestamo/abono";
			} else if (total == prestamo.getMonto()) {
				prestamo.setPagado(1);
			}
			prestamo.setAbonoTotal(total);
			prestamoDao.update(prestamo);
		}
		return "redirect:/prestamo";
	}

	@GetMapping({ "/abono/{id}" })
	public String editar(@PathVariable Integer id, Model model) {
		if (model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		Map<String, String> errores = new HashMap<>();
		model.addAttribute("errores", errores);
		Prestamo editar = prestamoDao.find(id);
		model.addAttribute("prestamo", editar);
		return "catalogo/prestamo/abono";
	}

}
