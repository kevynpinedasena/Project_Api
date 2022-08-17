package com.pets1.app.dto.entityData;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CambiarCotrasenaDto {
	
	@NotEmpty(message = "el correo no puede ser nulo ni vacio")
	@Email
	private String correo;
	
	@NotEmpty(message = "la nueva contraseña no puede ser nula ni vacia")
	private String nuevaContrasena;

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNuevaContrasena() {
		return nuevaContrasena;
	}

	public void setNuevaContrasena(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

}
