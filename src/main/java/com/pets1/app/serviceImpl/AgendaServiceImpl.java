package com.pets1.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pets1.app.domain.AgendaVo;
import com.pets1.app.domain.UsuarioVo;
import com.pets1.app.domain.VeterinarioVo;
import com.pets1.app.dto.answers.AgendaAnswerDto;
import com.pets1.app.dto.answers.AgendaUsuarioAnswerDto;
import com.pets1.app.dto.answers.AgendaVeterinarioAnswerDto;
import com.pets1.app.dto.entityData.AgendaDto;
import com.pets1.app.exeptions.AppPetsCareExeption;
import com.pets1.app.exeptions.ResourceNotFoudExeption;
import com.pets1.app.repository.IAgendaRepository;
import com.pets1.app.repository.IUsuarioRepository;
import com.pets1.app.repository.IVeterinarioRepository;
import com.pets1.app.service.IAgendaService;

@Service
@Transactional
public class AgendaServiceImpl implements IAgendaService{
	
	@Autowired 
	private IAgendaRepository agendaRepository;
	
	@Autowired 
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IVeterinarioRepository veterinarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private int CREADA = 1;
	private int ENCURSO = 2;
	private int CANCELADA = 3;
	private int FINALIZADA = 4;
	
	@Override
	public void CrearAgenda(Long documentoUsuario, Long documentoVeterinario, AgendaDto agendaDto) {
		UsuarioVo usuario = usuarioRepository.findById(documentoUsuario).orElseThrow(() -> new ResourceNotFoudExeption("usuario", "documento", documentoUsuario));
		VeterinarioVo veterinario = veterinarioRepository.findById(documentoVeterinario).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "documento", documentoVeterinario));
		
		AgendaVo datosCita = mapearEntidad(agendaDto);
		
		datosCita.setDocumentous(usuario);
		datosCita.setDocumentovt(veterinario);
		
		if (datosCita.getEstado() != CREADA && datosCita.getEstado() != ENCURSO && datosCita.getEstado() != CANCELADA && datosCita.getEstado() != FINALIZADA){
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "El estado no puede ser menor a 1 y mayor de 4");
		}
		
		agendaRepository.save(datosCita);
	}

	@Override
	public List<AgendaUsuarioAnswerDto> listaAgendaUsuario(Long documentoUsuario) {
		usuarioRepository.findById(documentoUsuario).orElseThrow(() -> new ResourceNotFoudExeption("usuario", "documento", documentoUsuario));
		List<AgendaVo> agendaDeUsuario = agendaRepository.findByDocumentousDocumentoUs(documentoUsuario);
		return agendaDeUsuario.stream().map(agenda -> mapearusuarioaswerDto(agenda)).collect(Collectors.toList());
	}

	@Override
	public List<AgendaVeterinarioAnswerDto> listaAgendaVeterinario(Long documentoVeterinario) {
		veterinarioRepository.findById(documentoVeterinario).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "documento", documentoVeterinario));
		List<AgendaVo> agendaDeVeterinario = agendaRepository.findByDocumentovtDocumento(documentoVeterinario);
		return agendaDeVeterinario.stream().map(agenda -> mapearVeteririnarioDto(agenda)).collect(Collectors.toList());
	}

	@Override
	public AgendaAnswerDto buscarAgendaId(Long codigo) {
		AgendaVo agenda = agendaRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoudExeption("agenda", "codigo", codigo));
		return mapearAgendaDto(agenda);
	}

	@Override
	public AgendaDto actualizarAgenda(Long codigo, AgendaDto agendaDto) {
		AgendaVo agenda = agendaRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoudExeption("agenda", "codigo", codigo));
		
		agenda.setFecha(agendaDto.getFecha());
		agenda.setHoraInicio(agendaDto.getHoraInicio());
		agenda.setHoraSalida(agendaDto.getHoraSalida());
		agenda.setNotas(agendaDto.getNotas());
		agenda.setEstado(agendaDto.getEstado());
		
		AgendaVo actualizarAgenda = agendaRepository.save(agenda);
		return mapearDto(actualizarAgenda);
	}

	@Override
	public void eliminarAgenda(Long codigo) {
		AgendaVo agenda = agendaRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoudExeption("agenda", "codigo", codigo));
		
		agendaRepository.delete(agenda);
	}
	
	
	private AgendaDto mapearDto(AgendaVo agendaVo) {
		AgendaDto agendaDto = modelMapper.map(agendaVo, AgendaDto.class);
		return agendaDto;
	}
	
	private AgendaAnswerDto mapearAgendaDto(AgendaVo agendaVo) {
		AgendaAnswerDto agendaAnswerDto = modelMapper.map(agendaVo, AgendaAnswerDto.class);
		return agendaAnswerDto;
	}
	
	private AgendaUsuarioAnswerDto mapearusuarioaswerDto(AgendaVo agendaVo) {
		AgendaUsuarioAnswerDto agendaUsuarioDto = modelMapper.map(agendaVo, AgendaUsuarioAnswerDto.class);
		return agendaUsuarioDto;
	}
	
	private AgendaVeterinarioAnswerDto mapearVeteririnarioDto(AgendaVo agendaVo) {
		AgendaVeterinarioAnswerDto agendaVeterinarioDto = modelMapper.map(agendaVo, AgendaVeterinarioAnswerDto.class);
		return agendaVeterinarioDto;
	}
	
	private AgendaVo mapearEntidad(AgendaDto agendaDto) {
		AgendaVo agendaVo = modelMapper.map(agendaDto, AgendaVo.class);
		return agendaVo;
	}
}