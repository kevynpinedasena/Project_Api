package com.pets1.app.dto.entityData;

import javax.validation.constraints.NotEmpty;

public class clinicaDto {
	
	private Long nit;
	
	@NotEmpty(message = "el nombre no puede ser nulo ni vacio")
	private String nombre;
	
	@NotEmpty(message = "la direccion no puede ser nula ni vacia")
	private String direccion;
	
	@NotEmpty
	private String telefono;
	
	@NotEmpty(message = "el correo no puede ser nulo ni vacio")
	private String correoCv;
	
	@NotEmpty(message = "la contraseña no puede ser nula ni vacia")
	private String password;
	
	private String imagenclinica;
	
	private int estadoCli;	

	public clinicaDto() {
		super();
	}

	public Long getNit() {
		return nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoCv() {
		return correoCv;
	}

	public void setCorreoCv(String correoCv) {
		this.correoCv = correoCv;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagenclinica() {
		return imagenclinica;
	}

	public void setImagenclinica(String imagenclinica) {
		this.imagenclinica = imagenclinica;
	}

	public int getEstadoCli() {
		return estadoCli;
	}

	public void setEstadoCli(int estadoCli) {
		this.estadoCli = estadoCli;
	}
		
}
