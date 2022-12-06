package com.chatbot.servicechatbot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name="USUARIO")
public class Usuario {
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRES")
	private String nombres;
	
	@Column(name = "NUMERO_IDENTIFICACION")
	private String numeroIdentificacion;
	
	@Column(name = "NUMERO_POLIZA")
	private String numeroPoliza;
	
	@Column(name = "PLACA_VEHICULO_ASEGURADO")
	private String placaVehiculoAsegurado;
	
	@Column(name = "FECHA_VIGENCIA_POLIZA")
	private Date fechaVigenciaPoliza;

}
