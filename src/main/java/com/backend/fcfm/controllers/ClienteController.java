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
import org.springframework.web.bind.support.SessionStatus;

import com.backend.fcfm.entitys.Cliente;
import com.backend.fcfm.models.dao.ClienteDao;

@Controller
@RequestMapping(path = "/cliente")
@SessionAttributes("usuario")
public class ClienteController {

	@Autowired
	private ClienteDao clienteDao;

	@GetMapping({ "", "/" })
	public String Clientes(Model model) {
		
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		System.out.print(usuario.getUser());
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		model.addAttribute("titulo", "Cliente");
		model.addAttribute("clientes", clienteDao.findAll());
		return "catalogo/cliente/lista";
	}

	@GetMapping({ "/informacion" })
	public String info(Model model) {
		if (model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		Cliente user = (Cliente) model.getAttribute("usuario");
		model.addAttribute("titulo", "Cliente");
		model.addAttribute("cliente", user);
		return "catalogo/cliente/info"; 
	}

	@GetMapping({ "/buscarNombre" })
	public String buscarNombre(@RequestParam(name = "nombre") String nombre, Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		model.addAttribute("titulo", "Cliente");
		model.addAttribute("clientes", clienteDao.findNombre(nombre));
		return "catalogo/cliente/lista";
	}

	@GetMapping({ "/buscarId" })
	public String buscarId(@RequestParam(name = "id") Integer id, Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		if (id != null && id >= 0) {
			model.addAttribute("titulo", "Cliente");
			List<Cliente> clientes = new ArrayList<>();
			Cliente cliente = clienteDao.find(id);
			if (cliente != null) {
				clientes.add(cliente);
			}
			model.addAttribute("clientes", clientes);
			return "catalogo/cliente/lista";
		}
		return "redirect:/cliente";

	}

	@GetMapping({ "/form" })
	public String form(Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		Cliente cliente = new Cliente();
		cliente.setTelefono(0l);
		model.addAttribute("cliente", cliente);
		return "catalogo/cliente/form";
	}

	@PostMapping({ "/guardar" })
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus sesion) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null ) {
			return "redirect:/login";
		}

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Cliente");
			return "catalogo/cliente/form";
		}
		
		clienteDao.insert(cliente);
		if (usuario.getUser() == "Admin") {
			return "redirect:/cliente";
		} else {
			model.addAttribute("usuario", cliente);
			return "redirect:/cliente/informacion";
		}
	}
	
	@GetMapping({ "/abonarCuenta" })
	public String abonar(Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null) {
			return "redirect:/login";
		}
		model.addAttribute("cliente", usuario);
		return "catalogo/cliente/abono";
	}

	@GetMapping({ "/form/{id}" })
	public String editar(@PathVariable Integer id, Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null || !usuario.getUser().equals("Admin")) {
			return "redirect:/login";
		}
		model.addAttribute("titulo", "Alumno");
		Cliente editar = clienteDao.find(id);
		model.addAttribute("cliente", editar);
		return "catalogo/cliente/form";

	}
	
	@GetMapping({ "/myInfo" })
	public String editarInfo( Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null) {
			return "redirect:/login";
		}
		model.addAttribute("titulo", "Alumno");
		model.addAttribute("cliente", usuario);
		return "catalogo/cliente/form";

	}
	

	@GetMapping({ "/eliminar/{id}" })
	public String eliminar(@PathVariable Integer id, Model model) {
		if (model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		if (id != null && id > 0) {
			clienteDao.delete(id);
		}
		return "redirect:/cliente";
	}
	
	@PostMapping({"/depositar"})
	public String depositar(@RequestParam(name = "depositar", required = true) Long depositar, Model model) {
		if (model.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		Map<String, String> errores = new HashMap<>();
		if(depositar == null || depositar <= 0) {
			errores.put("depositar", "Ingrese un valor valido");
			model.addAttribute("errores", errores);
			return "catalogo/cliente/abono";
		}
		
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		usuario.setMonto(usuario.getMonto() + depositar);
		clienteDao.update(usuario);
		return "catalogo/cliente/abono";
	}
	
	@PostMapping({"/retirar"})
	public String retirar(@RequestParam(name = "retirar", required = true) Long retirar, Model model) {
		Cliente usuario = (Cliente) model.getAttribute("usuario");
		if (usuario == null) {
			return "redirect:/login";
		}
		Map<String, String> errores = new HashMap<>();
		if(retirar == null || retirar <= 0 || retirar > usuario.getMonto()) {
			errores.put("retirar", "Ingrese un valor valido");
			model.addAttribute("errores", errores);
			return "catalogo/cliente/abono";
		}
		usuario.setMonto(usuario.getMonto() - retirar);
		clienteDao.update(usuario);
		return "catalogo/cliente/abono";
	}

}
