package com.pets1.app.dto.entityData;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class VeterinarioDto {
	
	private Long documento ;
	
	@NotEmpty(message = "el nombre no puede estar vacio o nulo")
	private String nombre ;
	
	@NotEmpty
	private String apellidos ;
	
	@NotEmpty
	private String sexovt;
	
	@NotEmpty
	private String telefono ;
	
	@NotEmpty(message = "el correo no puede estar vacio o nulo")
	@Email
	private String correo ;
	
	@NotEmpty
	private String especialidad ;
	
	@NotEmpty(message = "la contraseña no puede estar vacia o nula")
	private String password ;
	
	private String imagenVete;
	
	private int estadoVt;

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSexovt() {
		return sexovt;
	}

	public void setSexovt(String sexovt) {
		this.sexovt = sexovt;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagenVete() {
		return imagenVete;
	}

	public void setImagenVete(String imagenVete) {
		this.imagenVete = imagenVete;
	}

	public int getEstadoVt() {
		return estadoVt;
	}

	public void setEstadoVt(int estadoVt) {
		this.estadoVt = estadoVt;
	}
}