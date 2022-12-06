package com.chatbot.servicechatbot.service;

import java.util.List;

import com.chatbot.servicechatbot.entity.Usuario;

public interface UsuarioService {
	
	public abstract List<Usuario> getUsuario(String id);
	
	public abstract List<Usuario> getPlaca(String placa);

}
