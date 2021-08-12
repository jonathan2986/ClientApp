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
import com.clientapp.web.models.entity.ClienteDireccion;
import com.clientapp.web.models.service.IClienteDireccionService;
import com.clientapp.web.models.service.IClienteService;



@Controller
@RequestMapping("/views/direcciones")
public class ClienteDireccionController {

	@Autowired
	private IClienteDireccionService clienteDireccionService;
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/")
	public String listarDirecciones(Model model) {
		List<ClienteDireccion> listadoDirecciones = clienteDireccionService.listar();
		model.addAttribute("titulo", "Listado de direcciones");
		model.addAttribute("direcciones", listadoDirecciones);
		return "views/direcciones/listar";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		ClienteDireccion clienteDireccion = new ClienteDireccion();
		List<Cliente> listaCliente = clienteService.listar();
		
		model.addAttribute("titulo", "Formulario: Nueva Direccion");
		model.addAttribute("direcciones", clienteDireccion);
		model.addAttribute("clientes", listaCliente);
		
		return "views/direcciones/frmCrear";
	}
	
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute ClienteDireccion clienteDireccion, BindingResult result, Model model, RedirectAttributes attribute) {
		
		List<Cliente> listaCliente = clienteService.listar();
		
		if(result.hasErrors()) {
			
			model.addAttribute("titulo", "Formulario: Nueva Direccion");
			model.addAttribute("direcciones", clienteDireccion);
			model.addAttribute("clientes", listaCliente);
			System.out.println("Hubo Errores en el formulario");
			return "views/direcciones/frmCrear";
		}
		clienteDireccionService.guardar(clienteDireccion);
		attribute.addFlashAttribute("success", "Direccion guardada con exito!");
		return "redirect:/views/direcciones/";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idDireccion, Model model, RedirectAttributes attribute) {
		
		ClienteDireccion clienteDireccion = null;
		
		if(idDireccion > 0) {
			clienteDireccion = clienteDireccionService.buscarPorId(idDireccion);
			 if(clienteDireccion == null) {
				 attribute.addFlashAttribute("error", "El ID de la direccion no existe!");
				 return "redirect:/views/direcciones/";
			 }
		}else {
			attribute.addFlashAttribute("error", "Error con el ID de la direccion");
			return "redirect:/views/direcciones/";
		}
		
		List<Cliente> listaCliente = clienteService.listar();
		
		model.addAttribute("titulo", "Formulario: Editar Cliente");
		model.addAttribute("direcciones", clienteDireccion);
		model.addAttribute("clientes", listaCliente);
		return "views/direcciones/frmCrear";
	} 
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idDireccion, RedirectAttributes attribute) {
		
		ClienteDireccion clienteDireccion = null;
		
		if(idDireccion > 0) {
			clienteDireccion = clienteDireccionService.buscarPorId(idDireccion);
			 if(clienteDireccion == null) {
				 attribute.addFlashAttribute("error", "El ID de la direccion no existe!");
				 return "redirect:/views/direcciones/";
			 }
		}else {
			attribute.addFlashAttribute("error", "Error con el ID de la direccion");
			return "redirect:/views/direcciones/";
		}
		
		clienteDireccionService.eliminar(idDireccion);
		System.out.println("Registro eliminado correctamente");
		return "redirect:/views/direcciones/";
	} 
	
}
