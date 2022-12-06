package com.chatbot.servicechatbot.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatbot.servicechatbot.entity.Usuario;

@Repository("UsuarioRepository")
public interface RepositoryUsuario extends CrudRepository<Usuario, Long>{
	
	List<Usuario> findBynumeroIdentificacion(String numeroIdentificacion);
	
	List<Usuario> findByplacaVehiculoAsegurado(String numeroIdentificacion);
	
}
