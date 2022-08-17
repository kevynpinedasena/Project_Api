package com.pets1.app.dto.entityData;

public class HorariosDto {

	private Long idHorarios;
	
	private String diaHorarios;

	private String horaInicio;

	private String horaSalida;

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
}
