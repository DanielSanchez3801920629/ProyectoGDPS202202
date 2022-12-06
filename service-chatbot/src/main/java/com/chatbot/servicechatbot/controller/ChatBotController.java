package com.chatbot.servicechatbot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.servicechatbot.entity.Pqrs;
import com.chatbot.servicechatbot.entity.Usuario;
import com.chatbot.servicechatbot.service.PqrsServiceImpl;
import com.chatbot.servicechatbot.service.UsuarioServiceImpl;

@RestController
@RequestMapping("/chatbot-svc")
@CrossOrigin(origins="*")
public class ChatBotController {
	
	@Autowired
	@Qualifier("usuarioServiceImplement")
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	@Qualifier("pqrsServiceImpl")
	private PqrsServiceImpl pqrsService;
	
	
	
	@GetMapping(path="/findByDocument", produces={"application/json"})
	@CrossOrigin(methods = { RequestMethod.GET })
	public List<Usuario> getUsuariosByDocument(@RequestParam(name = "document") String document) throws Exception {
		try {
			return usuarioService.getUsuario(document);
		} catch (Exception e) {
			 throw e;
		}
	}
	
	@GetMapping(path="/findByPlaca", produces={"application/json"})
	@CrossOrigin(methods = { RequestMethod.GET })
	public List<Usuario> getUsuariosByPlaca(@RequestParam(name = "placa") String placa) throws Exception {
		try {
			return usuarioService.getPlaca(placa);
		} catch (Exception e) {
			 throw e;
		}
	}
	
	@PostMapping(path="/newPqrs", produces={"application/json"})
	@CrossOrigin(methods = { RequestMethod.POST })
	public Pqrs newPqrs(@RequestBody Pqrs pqrs) throws Exception {
		try {
			return pqrsService.addPqrs(pqrs);
		} catch (Exception e) {
			throw e;
		}
		
	}

}
