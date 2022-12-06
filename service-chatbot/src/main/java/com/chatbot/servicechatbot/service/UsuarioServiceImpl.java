package com.chatbot.servicechatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chatbot.servicechatbot.entity.Usuario;
import com.chatbot.servicechatbot.repository.RepositoryUsuario;

@Service("usuarioServiceImplement")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	@Qualifier("UsuarioRepository")
	private RepositoryUsuario repositoryUsuario;

	@Override
	public List<Usuario> getUsuario(String id) {
		
		return repositoryUsuario.findBynumeroIdentificacion(id);
	}

	@Override
	public List<Usuario> getPlaca(String placa) {
		
		return repositoryUsuario.findByplacaVehiculoAsegurado(placa);
	}

	
	

}
