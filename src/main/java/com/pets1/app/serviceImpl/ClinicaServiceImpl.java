package com.pets1.app.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pets1.app.domain.ClinicaVo;
import com.pets1.app.domain.RolVo;
import com.pets1.app.dto.answers.ClinicaAnswerDto;
import com.pets1.app.dto.answers.ClinicaPorNombreDto;
import com.pets1.app.dto.answers.ClinicayRolDto;
import com.pets1.app.dto.entityData.clinicaDto;
import com.pets1.app.exeptions.AppPetsCareExeption;
import com.pets1.app.exeptions.ResourceNotFoudExeption;
import com.pets1.app.repository.IClinicaRepository;
import com.pets1.app.repository.IRolRepository;
import com.pets1.app.service.IClinicaService;

@Service
@Transactional
public class ClinicaServiceImpl implements IClinicaService{
	
	@Autowired
	private IClinicaRepository clinicaRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IRolRepository rolRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private int ACTIVO = 1;
	private int INACTIVO = 2;
	private int SOLICITUD = 3;
	
	@Override
	public void crearClinica(clinicaDto clinicaDto) {
		boolean clinica = clinicaRepository.findById(clinicaDto.getNit()).isPresent();
		
		if(clinica == true) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "ya existe una clinica con este nit");
		}
		
		else if(clinicaRepository.existsByCorreoCv(clinicaDto.getCorreoCv())) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "Ya existe un clinica con este email" );
		}
		
		else if(clinicaDto.getEstadoCli() != ACTIVO && clinicaDto.getEstadoCli() != INACTIVO && clinicaDto.getEstadoCli() != SOLICITUD) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "el estado no puede ser mayor a 3 ni menor a 1" );
		}
		
		ClinicaVo clinicaDatos = mapearEntidad(clinicaDto);
		clinicaDatos.setPasswordCv(passwordEncoder.encode(clinicaDto.getPassword()));
		
		RolVo rol = rolRepository.findByNombre("ROLE_CLINICA").get();
		clinicaDatos.setRoles(Collections.singleton(rol));
		
		clinicaRepository.save(clinicaDatos);
	}

	@Override
	public List<ClinicaAnswerDto> consultarListaDeClinicas() {
		List<ClinicaVo> clinicas = clinicaRepository.findAll();
		return clinicas.stream().map(clinica -> mapearAnswerDto(clinica)).collect(Collectors.toList());
	}

	@Override
	public ClinicaAnswerDto consultarClinicaPorId(Long nitClinica) {
		ClinicaVo clinica = clinicaRepository.findById(nitClinica).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "nit", nitClinica));
		return mapearAnswerDto(clinica);
	}

	@Override
	public clinicaDto actualizarClinica(Long nitClinica, clinicaDto clinicaDto) {
		ClinicaVo clinica = clinicaRepository.findById(nitClinica).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "nit", nitClinica));
		
		clinica.setNombre(clinicaDto.getNombre());
		clinica.setDireccion(clinicaDto.getDireccion());
		clinica.setTelefono(clinicaDto.getTelefono());
		clinica.setCorreoCv(clinicaDto.getCorreoCv());
		clinica.setPasswordCv(passwordEncoder.encode(clinicaDto.getPassword()));
		clinica.setImagenclinica(clinicaDto.getImagenclinica());
		
		ClinicaVo clinicaActualizada = clinicaRepository.save(clinica);
		
		return mapearDto(clinicaActualizada);
	}

	@Override
	public void eliminarClinica(Long nitClinica) {
		ClinicaVo clinica = clinicaRepository.findById(nitClinica).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "nit", nitClinica));
		clinicaRepository.delete(clinica);
	}
	
	@Override
	public void actualizarEstado(int estado, Long nit) {
		ClinicaVo clinica = clinicaRepository.findById(nit).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "nit", nit));
		
		if(estado != ACTIVO && estado != INACTIVO && estado != SOLICITUD) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "el estado no puede ser mayor a 3 ni menor a 1" );
		}
		
		clinica.setEstadoCli(estado);	
		clinicaRepository.save(clinica);
	}
	
	@Override
	public ClinicaPorNombreDto buscarClinicaPorNombre(String nombre) {
		
		ClinicaVo clinicasRep = clinicaRepository.findByNombre(nombre).orElseThrow(() -> new ResourceNotFoudExeption("Clinica", "Nombre", nombre));
		
		ClinicaPorNombreDto clinicaPorNombreDto = new ClinicaPorNombreDto();
		
		List<String[]> clinica = clinicaRepository.clinicaPorNombre(nombre);
		
		for (String[] datos : clinica) {
			clinicaPorNombreDto.setNombre(datos[0].toString());
			clinicaPorNombreDto.setDireccion(datos[1].toString());
			clinicaPorNombreDto.setTelefono(datos[2].toString());
			clinicaPorNombreDto.setCorreoCv(datos[3].toString());
			clinicaPorNombreDto.setPassword(datos[4].toString());
			clinicaPorNombreDto.setImagenclinica(datos[5].toString());
		}
		return clinicaPorNombreDto;
	}
	
	@Override
	public ClinicayRolDto buscarClinicaYRol(Long nit) {
		ClinicayRolDto clinica = new ClinicayRolDto();
		List<String[]> cli = clinicaRepository.clinicayRoles(nit);
		
		for (String[] datos : cli) {
			clinica.setNit(datos[0].toString());
			clinica.setCorreo(datos[1].toString());
			clinica.setRol(datos[2].toString());
		}
		
		return clinica;
	}
	
	private clinicaDto mapearDto(ClinicaVo clinica) {
		clinicaDto clinicaDTO = modelMapper.map(clinica, clinicaDto.class);
		return clinicaDTO;
	}
	
	private ClinicaAnswerDto mapearAnswerDto(ClinicaVo clinica) {
		ClinicaAnswerDto clinicaAnswerDTO = modelMapper.map(clinica, ClinicaAnswerDto.class);
		return clinicaAnswerDTO;
	}
	
	private ClinicaVo mapearEntidad(clinicaDto clinicaDto) {
		ClinicaVo clinica = modelMapper.map(clinicaDto, ClinicaVo.class);
		return clinica;
	}

}