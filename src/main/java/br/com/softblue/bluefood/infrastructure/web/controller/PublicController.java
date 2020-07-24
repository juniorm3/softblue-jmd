package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.softblue.bluefood.domain.cliente.Cliente;

@Controller
@RequestMapping(path = "/public")
public class PublicController {
	
	@GetMapping("/cliente/new")
	public String newClient(Model model) {		
		Cliente cliente = new Cliente();
		cliente.setNome("Mario");
		model.addAttribute("cliente", cliente);
		
		return "cliente-cadastro";
	}

}
