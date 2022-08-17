package com.pets1.app.service;

import java.util.List;

import com.pets1.app.dto.answers.ClinicaAnswerDto;
import com.pets1.app.dto.answers.ClinicaPorNombreDto;
import com.pets1.app.dto.answers.ClinicayRolDto;
import com.pets1.app.dto.entityData.clinicaDto;

public interface IClinicaService {

	void crearClinica(clinicaDto clinicaDto);
	
	List<ClinicaAnswerDto> consultarListaDeClinicas();
	
	ClinicaAnswerDto consultarClinicaPorId(Long nitClinica);
	
	clinicaDto actualizarClinica(Long nitClinica, clinicaDto clinicaDto);
	
	void eliminarClinica(Long nitClinica);
	
	void actualizarEstado(int estado, Long nit);
	
	ClinicaPorNombreDto buscarClinicaPorNombre(String nombre);
	
	ClinicayRolDto buscarClinicaYRol(Long nit);
}
