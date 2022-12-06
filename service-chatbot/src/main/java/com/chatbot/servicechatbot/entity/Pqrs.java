package com.chatbot.servicechatbot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PQRS")
public class Pqrs {
	
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "PQRS_SEQ_GENERATOR", sequenceName = "PQRS_SEQ_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "PQRS_SEQ_GENERATOR", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "DOCUMENTO")
	private String documento;
	
	@Column(name = "COMENTARIOS_PQRS")
	private String comentariosPQRS;
	
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
}
