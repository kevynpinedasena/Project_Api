package com.pets1.app.dto.answers;

import com.pets1.app.dto.entityData.UsuarioDto;

public class MascotaAswerDto {
	
	private Long codigo;
	
	private String nombre;
	
	private String edad;
	
	private String sexo;
	
	private String raza;
	
	private String color;
	
	private double peso;
	
	private String discapacidad;
	
	private String tipoAnimal;
	
	private String imagenMascota;
	
	private UsuarioDto dueniomascota;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public String getImagenMascota() {
		return imagenMascota;
	}

	public void setImagenMascota(String imagenMascota) {
		this.imagenMascota = imagenMascota;
	}

	public UsuarioDto getDueniomascota() {
		return dueniomascota;
	}

	public void setDueniomascota(UsuarioDto dueniomascota) {
		this.dueniomascota = dueniomascota;
	}
	
}