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
@Table(name = "agenda_cita")
public class AgendaVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_agen")
	private Long codigoA;

	@Column(name = "fecha_agenda", nullable = false)
	private String fecha;
	
	@Column(name = "hora_inicio", nullable = false)
	private String horaInicio;
	
	@Column(name = "hora_salida", nullable = false)
	private String horaSalida;
	
	@Column(name = "notas")
	private String notas;
	
	@Column(name = "estado_agenda", nullable = false)
	private int estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "documento_us", nullable = false)
	private UsuarioVo documentous;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "documento_vt", nullable = false)
	private VeterinarioVo documentovt;
	
	public AgendaVo () {
		
	}

	public AgendaVo(Long codigoA, String fecha, String horaInicio, String horaSalida, String notas, int estado,
			UsuarioVo documentous, VeterinarioVo documentovt) {
		super();
		this.codigoA = codigoA;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaSalida = horaSalida;
		this.notas = notas;
		this.estado = estado;
		this.documentous = documentous;
		this.documentovt = documentovt;
	}

	public Long getCodigoA() {
		return codigoA;
	}

	public void setCodigoA(Long codigoA) {
		this.codigoA = codigoA;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public UsuarioVo getDocumentous() {
		return documentous;
	}

	public void setDocumentous(UsuarioVo documentous) {
		this.documentous = documentous;
	}

	public VeterinarioVo getDocumentovt() {
		return documentovt;
	}

	public void setDocumentovt(VeterinarioVo documentovt) {
		this.documentovt = documentovt;
	}
}