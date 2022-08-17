package com.pets1.app.service;

import java.util.List;

import com.pets1.app.dto.answers.AgendaAnswerDto;
import com.pets1.app.dto.answers.AgendaUsuarioAnswerDto;
import com.pets1.app.dto.answers.AgendaVeterinarioAnswerDto;
import com.pets1.app.dto.entityData.AgendaDto;

public interface IAgendaService {
	
	void CrearAgenda(Long documentoUsuario, Long documentoVeterinario, AgendaDto agendaDto);
	
	List<AgendaUsuarioAnswerDto> listaAgendaUsuario(Long documentoUsuario);
	
	List<AgendaVeterinarioAnswerDto> listaAgendaVeterinario(Long documentoVeterinario);
	
	AgendaAnswerDto buscarAgendaId(Long codigo);
	
	AgendaDto actualizarAgenda(Long codigo, AgendaDto agendaDto);
	
	void eliminarAgenda(Long codigo);

}
