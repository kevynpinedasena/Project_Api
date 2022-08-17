package com.pets1.app.service;

import java.util.List;

import com.pets1.app.dto.answers.HorariosClinicaAnswerDto;
import com.pets1.app.dto.answers.HorariosVeterinarioAnswerDto;
import com.pets1.app.dto.entityData.HorariosDto;

public interface IHorariosService {

	void CrearHorariosVeterinario(Long documentoVeterinario, HorariosDto horariosDto);
	
	void CrearHorariosClinica(Long nitClinica, HorariosDto horariosDto);
	
	List<HorariosClinicaAnswerDto> listaHorariosClinica(Long nitClinica);
	
	List<HorariosVeterinarioAnswerDto> listaHorariosVeterinario(Long documentoVeterinario);
	
	HorariosDto actualizarHorarios(Long idHorarios, HorariosDto horariosDto);
	
	void eliminarHorarios(Long idHorarios);
}
