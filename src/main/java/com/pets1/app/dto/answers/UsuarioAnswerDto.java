package com.pets1.app.dto.answers;

import java.util.HashSet;
import java.util.Set;

import com.pets1.app.dto.entityData.MascotaDto;

public class UsuarioAnswerDto {
	
	private Long documentoUs;
	
	private String nombreUs;
	
	private String apellidoUs;
	
	private String sexoUs;
	
	private String telefonoUs;
	
	private String correoUs;
	
	private String passwordUs;
	
	private String imagenUsu;
	
	private int estadoUs;
		
	private Set<MascotaDto> mascotas= new HashSet<>();
	
	private Set<AgendaUsuarioAnswerDto> agendas= new HashSet<>();

	public Long getDocumentoUs() {
		return documentoUs;
	}

	public void setDocumentoUs(Long documentoUs) {
		this.documentoUs = documentoUs;
	}

	public String getNombreUs() {
		return nombreUs;
	}

	public void setNombreUs(String nombreUs) {
		this.nombreUs = nombreUs;
	}

	public String getApellidoUs() {
		return apellidoUs;
	}

	public void setApellidoUs(String apellidoUs) {
		this.apellidoUs = apellidoUs;
	}

	public String getSexoUs() {
		return sexoUs;
	}

	public void setSexoUs(String sexoUs) {
		this.sexoUs = sexoUs;
	}

	public String getTelefonoUs() {
		return telefonoUs;
	}

	public void setTelefonoUs(String telefonoUs) {
		this.telefonoUs = telefonoUs;
	}

	public String getCorreoUs() {
		return correoUs;
	}

	public void setCorreoUs(String correoUs) {
		this.correoUs = correoUs;
	}

	public String getPasswordUs() {
		return passwordUs;
	}

	public void setPasswordUs(String passwordUs) {
		this.passwordUs = passwordUs;
	}

	public String getImagenUsu() {
		return imagenUsu;
	}

	public void setImagenUsu(String imagenUsu) {
		this.imagenUsu = imagenUsu;
	}

	public int getEstadoUs() {
		return estadoUs;
	}

	public void setEstadoUs(int estadoUs) {
		this.estadoUs = estadoUs;
	}

	public Set<MascotaDto> getMascotas() {
		return mascotas;
	}

	public void setMascotas(Set<MascotaDto> mascotas) {
		this.mascotas = mascotas;
	}

	public Set<AgendaUsuarioAnswerDto> getAgendas() {
		return agendas;
	}

	public void setAgendas(Set<AgendaUsuarioAnswerDto> agendas) {
		this.agendas = agendas;
	}
}