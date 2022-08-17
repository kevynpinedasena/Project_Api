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
import com.pets1.app.domain.VeterinarioVo;
import com.pets1.app.dto.answers.VeterinarioAnswerDto;
import com.pets1.app.dto.answers.VeterinarioPorNombreDto;
import com.pets1.app.dto.answers.VeterinarioYRolesDto;
import com.pets1.app.dto.entityData.VeterinarioDto;
import com.pets1.app.exeptions.AppPetsCareExeption;
import com.pets1.app.exeptions.ResourceNotFoudExeption;
import com.pets1.app.repository.IClinicaRepository;
import com.pets1.app.repository.IRolRepository;
import com.pets1.app.repository.IVeterinarioRepository;
import com.pets1.app.service.IVeterinarioService;

@Service
@Transactional
public class VeterinarioServiceImpl implements IVeterinarioService{

	@Autowired
	private IVeterinarioRepository veterinarioRepository;
	
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

	@Override
	public void guardarVeterinarios(Long nitClinica, VeterinarioDto veterinarioDto) {
		ClinicaVo clinica = clinicaRepository.findById(nitClinica).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "nit", nitClinica));
		
		boolean vete = veterinarioRepository.findById(veterinarioDto.getDocumento()).isPresent();
		
		if (vete == true) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "el veterinario ya existe con este documento");
		}
		else if (veterinarioRepository.existsByCorreo(veterinarioDto.getCorreo())) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "Ya existe un veterinario con este correo" );
		}
		
		VeterinarioVo veterinario = mapearEntidad(veterinarioDto);
		veterinario.setClinica(clinica);
		veterinario.setPassword(passwordEncoder.encode(veterinarioDto.getPassword()));
		
		if (veterinario.getEstadoVt() != ACTIVO && veterinario.getEstadoVt() != INACTIVO) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "El estado no puede ser mayor a 2 y menor de 1");
		}
		
		RolVo rol = rolRepository.findByNombre("ROLE_VETERINARIO").get();
		veterinario.setRoles(Collections.singleton(rol));
		
		veterinarioRepository.save(veterinario);
	}

	@Override
	public List<VeterinarioAnswerDto> listaDeVeterinariosPorClinica(Long nitClinica) {
		clinicaRepository.findById(nitClinica).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "nit", nitClinica));
		List<VeterinarioVo> veterinarios = veterinarioRepository.findByclinicaNit(nitClinica);
		return veterinarios.stream().map(vaterinario -> mapearAnswerDto(vaterinario)).collect(Collectors.toList());
	}

	@Override
	public VeterinarioDto buscarVeterinarioId(Long documentoVeterinario) {
		VeterinarioVo veterinario = veterinarioRepository.findById(documentoVeterinario).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "documento", documentoVeterinario));
		return mapearDto(veterinario);
	}
	
	@Override
	public VeterinarioDto actualizarVeterinario(Long documentoVeterinario, VeterinarioDto veterinarioDto) {
		VeterinarioVo veterinario = veterinarioRepository.findById(documentoVeterinario).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "documento", documentoVeterinario));
		
		veterinario.setNombre(veterinarioDto.getNombre());
		veterinario.setApellidos(veterinarioDto.getApellidos());
		veterinario.setSexovt(veterinarioDto.getSexovt());
		veterinario.setTelefono(veterinarioDto.getTelefono());
		veterinario.setCorreo(veterinarioDto.getCorreo());
		veterinario.setEspecialidad(veterinarioDto.getEspecialidad());
		veterinario.setPassword(passwordEncoder.encode(veterinarioDto.getPassword()));
		veterinario.setImagenVete(veterinarioDto.getImagenVete());
		
		VeterinarioVo asctualizarVeterinario = veterinarioRepository.save(veterinario);
		
		return mapearDto(asctualizarVeterinario);
	}

	@Override
	public void eliminarVeterinario(Long documentoVeterinario) {
		VeterinarioVo veterinario = veterinarioRepository.findById(documentoVeterinario).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "documento", documentoVeterinario));
		veterinarioRepository.delete(veterinario);
	}
	

	@Override
	public void deshabilitarEstadoVeterinario(int estadoVt, Long documento) {
		VeterinarioVo veterinario = veterinarioRepository.findById(documento).orElseThrow(() -> new ResourceNotFoudExeption("veterinario", "documento", documento));
		
		if (estadoVt != ACTIVO && estadoVt != INACTIVO) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "El estado no puede ser mayor a 2 y menor de 1");
		}
		
		veterinario.setEstadoVt(estadoVt);
		veterinarioRepository.save(veterinario);
	}
	
	@Override
	public VeterinarioPorNombreDto buscarVeterinarioPorNombre(String nombre) {
		
		VeterinarioVo vete = veterinarioRepository.findByNombre(nombre).orElseThrow(() -> new ResourceNotFoudExeption("Veterinario", "nombre", nombre));
		
		VeterinarioPorNombreDto veterinarioPorNombre = new VeterinarioPorNombreDto();
		
		List<String[]> veterinario = veterinarioRepository.veterinarioPorNombre(nombre);
		
		for (String[] datos : veterinario) {
			veterinarioPorNombre.setNombre(datos[0].toString());
			veterinarioPorNombre.setApellidos(datos[1].toString());
			veterinarioPorNombre.setSexovt(datos[2].toString());
			veterinarioPorNombre.setTelefono(datos[3].toString());
			veterinarioPorNombre.setCorreo(datos[4].toString());
			veterinarioPorNombre.setEspecialidad(datos[5].toString());
			veterinarioPorNombre.setPassword(datos[6].toString());
			veterinarioPorNombre.setImagenVete(datos[7].toString());
		}
		return veterinarioPorNombre;
	}
	
	@Override
	public VeterinarioYRolesDto buscarVeterinarioYRoles(Long documento) {
		VeterinarioYRolesDto veterinario = new VeterinarioYRolesDto();
		List<String[]> vete = veterinarioRepository.veterinarioyRoles(documento);
		
		for (String[] datos : vete) {
			veterinario.setDocumento(datos[0].toString());
			veterinario.setCorreoVt(datos[1].toString());
			veterinario.setRol(datos[2].toString());
		}
		
		return veterinario;
	}
	
	private VeterinarioDto mapearDto(VeterinarioVo veterinarioVo) {
		VeterinarioDto veterinarioDto = modelMapper.map(veterinarioVo, VeterinarioDto.class);
		return veterinarioDto;
	}
	
	private VeterinarioAnswerDto mapearAnswerDto(VeterinarioVo veterinarioVo) {
		VeterinarioAnswerDto veterinarioAnswerDto = modelMapper.map(veterinarioVo, VeterinarioAnswerDto.class);
		return veterinarioAnswerDto;
	}
	
	private VeterinarioVo mapearEntidad(VeterinarioDto veterinarioDto) {
		VeterinarioVo veterinarioVo = modelMapper.map(veterinarioDto, VeterinarioVo.class);
		return veterinarioVo;
	}

}