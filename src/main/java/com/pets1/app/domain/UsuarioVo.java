package com.pets1.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"correo_usu"})})
public class UsuarioVo {
	
	@Id
	@Column(name = "documento_usu", nullable = false)
	private Long documentoUs;

	@Column(name = "nombre_usu", nullable = false)
	private String nombreUs;

	@Column(name = "apellido_usu", nullable = false)
	private String apellidoUs;
	
	@Column(name = "sexo_usu", nullable = false)
	private String sexoUs;

	@Column(name = "telefono_usu", nullable = false)
	private String telefonoUs;
	
	@Column(name = "correo_usu", nullable = false)
	private String correoUs;

	@Column(name = "password_usu", nullable = false)
	private String passwordUs;
	
	@Column(name = "foto_usu", nullable = false, length = 400)
	private String imagenUsu;
	
	@Column(name = "estado_usu", nullable = false)
	private int estadoUs;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_doc", referencedColumnName = "documento_usu"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Set<RolVo> roles = new HashSet<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "dueniomascota", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MascotaVo> mascotas= new HashSet<>();
	
	@JsonIgnoreProperties
	@OneToMany(mappedBy = "documentous", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AgendaVo> agendas= new HashSet<>();
	
	public UsuarioVo() {
		super();
	}

	public UsuarioVo(Long documentoUs, String nombreUs, String apellidoUs, String sexoUs, String telefonoUs,
			String correoUs, String passwordUs, String imagenUsu, int estadoUs, Set<RolVo> roles,
			Set<MascotaVo> mascotas, Set<AgendaVo> agendas) {
		super();
		this.documentoUs = documentoUs;
		this.nombreUs = nombreUs;
		this.apellidoUs = apellidoUs;
		this.sexoUs = sexoUs;
		this.telefonoUs = telefonoUs;
		this.correoUs = correoUs;
		this.passwordUs = passwordUs;
		this.imagenUsu = imagenUsu;
		this.estadoUs = estadoUs;
		this.roles = roles;
		this.mascotas = mascotas;
		this.agendas = agendas;
	}

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

	public Set<RolVo> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolVo> roles) {
		this.roles = roles;
	}

	public Set<MascotaVo> getMascotas() {
		return mascotas;
	}

	public void setMascotas(Set<MascotaVo> mascotas) {
		this.mascotas = mascotas;
	}

	public Set<AgendaVo> getAgendas() {
		return agendas;
	}

	public void setAgendas(Set<AgendaVo> agendas) {
		this.agendas = agendas;
	}
}