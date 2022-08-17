package com.pets1.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pets1.app.domain.ClinicaVo;
import com.pets1.app.domain.HorariosVo;
import com.pets1.app.domain.VeterinarioVo;
import com.pets1.app.dto.answers.HorariosClinicaAnswerDto;
import com.pets1.app.dto.answers.HorariosVeterinarioAnswerDto;
import com.pets1.app.dto.entityData.HorariosDto;
import com.pets1.app.exeptions.ResourceNotFoudExeption;
import com.pets1.app.repository.IClinicaRepository;
import com.pets1.app.repository.IHorariosRepository;
import com.pets1.app.repository.IVeterinarioRepository;
import com.pets1.app.service.IHorariosService;

@Service
@Transactional
public class HorariosServiceImpl implements IHorariosService{
	
	@Autowired
	private IHorariosRepository horariosRepository;
	
	@Autowired
	private IClinicaRepository clinicaRepository;
	
	@Autowired
	private IVeterinarioRepository veterinarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void CrearHorariosVeterinario(Long documentoVeterinario, HorariosDto horariosDto) {
		VeterinarioVo veterinarioVo = veterinarioRepository.findById(documentoVeterinario).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "documento", documentoVeterinario));
		
		HorariosVo datosHorarios = mapearEntidad(horariosDto);
		datosHorarios.setDocumentovt(veterinarioVo);
		horariosRepository.save(datosHorarios);
	}
	
	@Override
	public void CrearHorariosClinica(Long nitClinica, HorariosDto horariosDto) {
		ClinicaVo clinicaVo = clinicaRepository.findById(nitClinica).orElseThrow(() -> new ResourceNotFoudExeption("Clinica", "documento", nitClinica));
		
		HorariosVo datosHorarios = mapearEntidad(horariosDto);
		datosHorarios.setNitCli(clinicaVo);
		horariosRepository.save(datosHorarios);
	}

	@Override
	public List<HorariosClinicaAnswerDto> listaHorariosClinica(Long nitClinica) {
		clinicaRepository.findById(nitClinica).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "nit", nitClinica));
		List<HorariosVo> horariosDeClinica = horariosRepository.findBynitCliNit(nitClinica);
		return horariosDeClinica.stream().map(horarios -> mapearClinicaAnswerDto(horarios)).collect(Collectors.toList());
	}

	@Override
	public List<HorariosVeterinarioAnswerDto> listaHorariosVeterinario(Long documentoVeterinario) {
		veterinarioRepository.findById(documentoVeterinario).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "documento", documentoVeterinario));
		List<HorariosVo> HorariosDeVeterinario = horariosRepository.findByDocumentovtDocumento(documentoVeterinario);
		return HorariosDeVeterinario.stream().map(horarios -> mapearVeterinarioAnswerDto(horarios)).collect(Collectors.toList());
	}

	@Override
	public HorariosDto actualizarHorarios(Long idHorarios, HorariosDto horariosDto) {
		HorariosVo horarios = horariosRepository.findById(idHorarios).orElseThrow();
		
		horarios.setDiaHorarios(horariosDto.getDiaHorarios());
		horarios.setHoraInicio(horariosDto.getHoraInicio());
		horarios.setHoraSalida(horariosDto.getHoraSalida());
		
		HorariosVo actualizarHorarios = horariosRepository.save(horarios);
		return mapearDto(actualizarHorarios);
	}

	@Override
	public void eliminarHorarios(Long idHorarios) {
		HorariosVo horarios = horariosRepository.findById(idHorarios).orElseThrow();
		horariosRepository.delete(horarios);
	}
	
	private HorariosVo mapearEntidad(HorariosDto horariosDto) {
		return modelMapper.map(horariosDto, HorariosVo.class);
	}
	
	private HorariosClinicaAnswerDto mapearClinicaAnswerDto(HorariosVo horariosVo) {
		HorariosClinicaAnswerDto horarioClinicaDto = modelMapper.map(horariosVo, HorariosClinicaAnswerDto.class);
		return horarioClinicaDto;
	}
	
	private HorariosVeterinarioAnswerDto mapearVeterinarioAnswerDto(HorariosVo horariosVo) {
		HorariosVeterinarioAnswerDto horarioVeterinarioDto = modelMapper.map(horariosVo, HorariosVeterinarioAnswerDto.class);
		return horarioVeterinarioDto;
	}
	
	private HorariosDto mapearDto(HorariosVo horariosVo) {
		HorariosDto horariosDto = modelMapper.map(horariosVo, HorariosDto.class);
		return horariosDto;
	}
}
