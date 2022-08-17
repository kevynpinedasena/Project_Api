package com.pets1.app.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoudExeption extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String nombreDelRecurso;
	private String nombreDelCampo;
	private Long valorDelCampo;
	private String valorDelCorreo;
	
	public ResourceNotFoudExeption(String nombreDelRecurso, String nombreDelCampo, Long valorDelCampo) {
		super(String.format("%s no encontrada con : %s : %s", nombreDelRecurso, nombreDelCampo, valorDelCampo));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCampo = valorDelCampo;
	}
	
	public ResourceNotFoudExeption(String nombreDelRecurso, String nombreDelCampo, String valorDelCorreo) {
		super(String.format("%s no encontrada con : %s : %s", nombreDelRecurso, nombreDelCampo, valorDelCorreo));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCorreo = valorDelCorreo;
	}

	public String getNombreDelRecurso() {
		return nombreDelRecurso;
	}

	public void setNombreDelRecurso(String nombreDelRecurso) {
		this.nombreDelRecurso = nombreDelRecurso;
	}

	public String getNombreDelCampo() {
		return nombreDelCampo;
	}

	public void setNombreDelCampo(String nombreDelCampo) {
		this.nombreDelCampo = nombreDelCampo;
	}

	public Long getValorDelCampo() {
		return valorDelCampo;
	}

	public void setValorDelCampo(Long valorDelCampo) {
		this.valorDelCampo = valorDelCampo;
	}
}