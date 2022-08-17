package com.pets1.app.dto.entityData;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UsuarioDto {
	
	private Long documentoUs;
	
	@NotEmpty(message = "el nombre no puede estar vacio o nulo")
	private String nombreUs;
	
	@NotEmpty
	private String apellidoUs;
	
	@NotEmpty
	private String sexoUs;
	
	@NotEmpty
	private String telefonoUs;
	
	@NotEmpty(message = "el correo no puede estar vacio o nulo")
	@Email
	private String correoUs;
	
	@NotEmpty(message = "la contraseña no puede estar vacia o nula")
	private String passwordUs;
	
	private String imagenUsu;
	
	private int estadoUs;
	

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
}