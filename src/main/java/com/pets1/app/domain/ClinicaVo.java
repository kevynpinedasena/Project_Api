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

@Entity
@Table(name = "clinica_veterinaria",uniqueConstraints = {@UniqueConstraint(columnNames = {"correo_cv"})})
public class ClinicaVo {
	
	@Id
	@Column(name = "nit_cv", nullable = false)
	private Long nit;
	
	@Column(name = "nombre_cv", nullable = false)
	private String nombre;
	
	@Column(name = "direccion_cv", nullable = false)
	private String direccion; 
	
	@Column(name = "telefono_cv", nullable = false)
	private String telefono; 
	
	@Column(name = "correo_cv", nullable = false)
	private String correoCv;

	@Column(name = "password_cv", nullable = false)
	private String passwordCv;
	
	@Column(name = "foto_clinica", nullable = false, length = 400)
	private String imagenclinica;
	
	@Column(name = "estado_cli", nullable = false)
	private int estadoCli;
	
	@JsonBackReference
	@OneToMany(mappedBy = "clinica", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<VeterinarioVo> veterinarios = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "clinica_roles", joinColumns = @JoinColumn(name = "clinica_nit", referencedColumnName = "nit_cv"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Set<RolVo> roles = new HashSet<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "nitCli", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<HorariosVo> horarios = new HashSet<>();
	
	public ClinicaVo () {
		
	}

	public ClinicaVo(Long nit, String nombre, String direccion, String telefono, String correoCv, String passwordCv,
			String imagenclinica, int estadoCli, Set<VeterinarioVo> veterinarios, Set<RolVo> roles,
			Set<HorariosVo> horarios) {
		super();
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correoCv = correoCv;
		this.passwordCv = passwordCv;
		this.imagenclinica = imagenclinica;
		this.estadoCli = estadoCli;
		this.veterinarios = veterinarios;
		this.roles = roles;
		this.horarios = horarios;
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

	public String getPasswordCv() {
		return passwordCv;
	}

	public void setPasswordCv(String passwordCv) {
		this.passwordCv = passwordCv;
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

	public Set<VeterinarioVo> getVeterinarios() {
		return veterinarios;
	}

	public void setVeterinarios(Set<VeterinarioVo> veterinarios) {
		this.veterinarios = veterinarios;
	}

	public Set<RolVo> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolVo> roles) {
		this.roles = roles;
	}

	public Set<HorariosVo> getHorarios() {
		return horarios;
	}

	public void setHorarios(Set<HorariosVo> horarios) {
		this.horarios = horarios;
	}

}