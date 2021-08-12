package com.clientapp.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clientapp.web.models.entity.Cliente;
import com.clientapp.web.models.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/")
	public String listarClientes(Model model) {
		
		List<Cliente> listadoClientes = clienteService.listar();
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoClientes);
		return "views/clientes/listar";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo", "Formulario: Nuevo Cliente");
		model.addAttribute("cliente", cliente);
		return "views/clientes/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model, RedirectAttributes attribute) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("titulo", "Formulario: Nuevo Cliente");
			model.addAttribute("cliente", cliente);
			System.out.println("Hubo Errores en el formulario");
			return "views/clientes/frmCrear";
		}
		clienteService.guardar(cliente);
		attribute.addFlashAttribute("success", "Cliente guardado con exito!");
		return "redirect:/views/clientes/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idCliente, Model model, RedirectAttributes attribute) {
		
		Cliente cliente = null;
		
		if(idCliente > 0) {
			 cliente = clienteService.buscarPorId(idCliente);
			 if(cliente == null) {
				 attribute.addFlashAttribute("error", "El ID del cliente no existe!");
				 return "redirect:/views/clientes/";
			 }
		}else {
			attribute.addFlashAttribute("error", "Error con el ID del cliente");
			return "redirect:/views/clientes/";
		}
		
		model.addAttribute("titulo", "Formulario: Editar Cliente");
		model.addAttribute("cliente", cliente);
		return "views/clientes/frmCrear";
	} 
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idCliente, RedirectAttributes attribute) {
		
		Cliente cliente = null;
		
		if(idCliente > 0) {
			 cliente = clienteService.buscarPorId(idCliente);
			 if(cliente == null) {
				 attribute.addFlashAttribute("error", "El ID del cliente no existe!");
				 return "redirect:/views/clientes/";
			 }
		}else {
			attribute.addFlashAttribute("error", "Error con el ID del cliente");
			return "redirect:/views/clientes/";
		}
		
		clienteService.eliminar(idCliente);
		System.out.println("Registro eliminado correctamente");
		return "redirect:/views/clientes/";
	} 
}
