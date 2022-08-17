package com.pets1.app.dto.answers;

import java.util.HashSet;
import java.util.Set;

import com.pets1.app.dto.entityData.VeterinarioDto;

public class ClinicaAnswerDto {
	
	private Long nit;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private String correoCv;
	
	private String password;
	
	private String imagenclinica;
	
	private int estadoCli;
	
	private Set<VeterinarioDto> veterinarios = new HashSet<>();
	
	private Set<HorariosClinicaAnswerDto> horarios = new HashSet<>();

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

	public Set<VeterinarioDto> getVeterinarios() {
		return veterinarios;
	}

	public void setVeterinarios(Set<VeterinarioDto> veterinarios) {
		this.veterinarios = veterinarios;
	}

	public Set<HorariosClinicaAnswerDto> getHorarios() {
		return horarios;
	}

	public void setHorarios(Set<HorariosClinicaAnswerDto> horarios) {
		this.horarios = horarios;
	}
}