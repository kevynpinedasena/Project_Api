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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "veterinario", uniqueConstraints = {@UniqueConstraint(columnNames = {"correo_vt"})})
public class VeterinarioVo {
	
	@Id
	@Column(name = "documento_vt", nullable=false)
	private Long documento ;

	@Column(name = "nombre_vt", nullable=false) 
	private String nombre ;

	@Column(name = "apellido_vt", nullable = false) 
	private String apellidos ;
	
	@Column(name = "sexo_vt", nullable = false)
	private String sexovt;

	@Column(name = "telefono_vt", nullable = false) 
	private String telefono ;

	@Column(name = "correo_vt", nullable = false) 
	private String correo ;

	@Column(name = "especialidad_vt", nullable = false) 
	private String especialidad ;

	@Column(name = "password_vt", nullable = false) 
	private String password ;
	
	@Column(name = "foto_veterinario", nullable = false, length = 400)
	private String imagenVete;
	
	@Column(name = "estado_vt", nullable = false)
	private int estadoVt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinica_nit", nullable = false)
	private ClinicaVo clinica;
	
	@JsonBackReference
	@OneToMany(mappedBy = "documentovt", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AgendaVo> agendas= new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "veterinarios_roles", joinColumns = @JoinColumn(name = "veterinario_doc", referencedColumnName = "documento_vt"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Set<RolVo> roles = new HashSet<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "documentovt", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<HorariosVo> horarios = new HashSet<>();
	
	public VeterinarioVo () {
		
	}

	public VeterinarioVo(Long documento, String nombre, String apellidos, String sexovt, String telefono, String correo,
			String especialidad, String password, String imagenVete, int estadoVt, ClinicaVo clinica,
			Set<AgendaVo> agendas, Set<RolVo> roles, Set<HorariosVo> horarios) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sexovt = sexovt;
		this.telefono = telefono;
		this.correo = correo;
		this.especialidad = especialidad;
		this.password = password;
		this.imagenVete = imagenVete;
		this.estadoVt = estadoVt;
		this.clinica = clinica;
		this.agendas = agendas;
		this.roles = roles;
		this.horarios = horarios;
	}

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

	public ClinicaVo getClinica() {
		return clinica;
	}

	public void setClinica(ClinicaVo clinica) {
		this.clinica = clinica;
	}

	public Set<AgendaVo> getAgendas() {
		return agendas;
	}

	public void setAgendas(Set<AgendaVo> agendas) {
		this.agendas = agendas;
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