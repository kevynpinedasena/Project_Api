package com.pets1.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mascotas")
public class MascotaVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_mc", nullable = false, unique = true)
	private Long codigo;
	
	@Column(name = "nombre_mc", nullable = false)
	private String nombre;
	
	@Column(name = "edad_mc", nullable = false)
	private String edad;
	
	@Column(name = "sexo_mc", nullable = false)
	private String sexo;
	
	@Column(name = "raza_mc", nullable = false)
	private String raza;
	
	@Column(name = "color_mc", nullable = false)
	private String color;
	
	@Column(name = "peso_mc", nullable = false)
	private double peso;
	
	@Column(name = "discapacidad_mc", nullable = false)
	private String discapacidad;
	
	@Column(name = "tipoAnimal_mc", nullable = false)
	private String tipoAnimal;
	
	@Column(name = "foto_mascota", nullable = false, length = 400)
	private String imagenMascota;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "documento_usu", nullable = false)
	private UsuarioVo dueniomascota;
	
	public MascotaVo () {
		
	}

	public MascotaVo(Long codigo, String nombre, String edad, String sexo, String raza, String color, double peso,
			String discapacidad, String tipoAnimal, String imagenMascota, UsuarioVo dueniomascota) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.raza = raza;
		this.color = color;
		this.peso = peso;
		this.discapacidad = discapacidad;
		this.tipoAnimal = tipoAnimal;
		this.imagenMascota = imagenMascota;
		this.dueniomascota = dueniomascota;
	}

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

	public UsuarioVo getDueniomascota() {
		return dueniomascota;
	}

	public void setDueniomascota(UsuarioVo dueniomascota) {
		this.dueniomascota = dueniomascota;
	}
}