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
@Table(name = "horarios")
public class HorariosVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horarios")
	private Long idHorarios;
	
	@Column(name = "dia_horarios", nullable = false)
	private String diaHorarios;
	
	@Column(name = "hora_inicio", nullable = false)
	private String horaInicio;
	
	@Column(name = "hora_salida", nullable = false)
	private String horaSalida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nit_clinica")
	private ClinicaVo nitCli;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "documento_vt")
	private VeterinarioVo documentovt;

	public HorariosVo() {

	}

	public HorariosVo(Long idHorarios, String diaHorarios, String horaInicio, String horaSalida, ClinicaVo nitCli,
			VeterinarioVo documentovt) {
		super();
		this.idHorarios = idHorarios;
		this.diaHorarios = diaHorarios;
		this.horaInicio = horaInicio;
		this.horaSalida = horaSalida;
		this.nitCli = nitCli;
		this.documentovt = documentovt;
	}

	public Long getIdHorarios() {
		return idHorarios;
	}

	public void setIdHorarios(Long idHorarios) {
		this.idHorarios = idHorarios;
	}

	public String getDiaHorarios() {
		return diaHorarios;
	}

	public void setDiaHorarios(String diaHorarios) {
		this.diaHorarios = diaHorarios;
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

	public ClinicaVo getNitCli() {
		return nitCli;
	}

	public void setNitCli(ClinicaVo nitCli) {
		this.nitCli = nitCli;
	}

	public VeterinarioVo getDocumentovt() {
		return documentovt;
	}

	public void setDocumentovt(VeterinarioVo documentovt) {
		this.documentovt = documentovt;
	}
}