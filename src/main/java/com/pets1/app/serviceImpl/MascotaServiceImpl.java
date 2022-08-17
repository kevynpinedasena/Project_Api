package com.pets1.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pets1.app.domain.MascotaVo;
import com.pets1.app.domain.UsuarioVo;
import com.pets1.app.dto.answers.MascotaAswerDto;
import com.pets1.app.dto.answers.MascotaPorNombreDto;
import com.pets1.app.dto.entityData.MascotaDto;
import com.pets1.app.exeptions.AppPetsCareExeption;
import com.pets1.app.exeptions.ResourceNotFoudExeption;
import com.pets1.app.repository.IMascotaRepository;
import com.pets1.app.repository.IUsuarioRepository;
import com.pets1.app.service.IMascotaService;

@Service
@Transactional
public class MascotaServiceImpl implements IMascotaService{

	@Autowired
	private IMascotaRepository mascotaRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void guardarMascota(Long documentoUsuario, MascotaDto mascotaDto) {
		MascotaVo mascota = mapearEntidad(mascotaDto);
		UsuarioVo usuario = usuarioRepository.findById(documentoUsuario).orElseThrow(() -> new ResourceNotFoudExeption("Usuario", "documento", documentoUsuario));
		
		mascota.setDueniomascota(usuario);
		
		int edad = Integer.parseInt(mascota.getEdad());
		
		if (edad <= 0 || edad > 50) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "La Edad no puede ser menor a 0 y mayor de 50");
		}
		
		mascotaRepository.save(mascota);	
	}

	@Override
	public List<MascotaAswerDto> obtenerMascotasDeUsuario(Long documentoUsuario) {
		usuarioRepository.findById(documentoUsuario).orElseThrow(() -> new ResourceNotFoudExeption("Usuario", "documento", documentoUsuario));
		List<MascotaVo> mascotas=mascotaRepository.findByDueniomascotaDocumentoUs(documentoUsuario);
		return mascotas.stream().map(mascota -> mapearAnswerDto(mascota)).collect(Collectors.toList());
		
	}

	@Override
	public MascotaDto obtenerMascotaId(Long documentoUsuario, Long codigoMascota) {
		UsuarioVo usuario = usuarioRepository.findById(documentoUsuario).orElseThrow(() -> new ResourceNotFoudExeption("Usuario", "documento", documentoUsuario));
		MascotaVo mascota = mascotaRepository.findById(codigoMascota).orElseThrow(() -> new ResourceNotFoudExeption("Mascota", "codigo", codigoMascota));
		
		if(!mascota.getDueniomascota().getDocumentoUs().equals(usuario.getDocumentoUs())) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "la mascota no pertenese a este usuario");
			
		}
		
		return mapearDto(mascota);
	}

	@Override
	public MascotaDto actualizarMascota(Long documentoUsuario, Long codigoMascota, MascotaDto mascotaDto) {
		UsuarioVo usuario = usuarioRepository.findById(documentoUsuario).orElseThrow(() -> new ResourceNotFoudExeption("Usuario", "documento", documentoUsuario));
		MascotaVo mascota = mascotaRepository.findById(codigoMascota).orElseThrow(() -> new ResourceNotFoudExeption("Mascota", "codigo", codigoMascota));
		
		if(!mascota.getDueniomascota().getDocumentoUs().equals(usuario.getDocumentoUs())) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "la mascota no pertenese a este usuario");
		}
		
		mascota.setNombre(mascotaDto.getNombre());
		mascota.setEdad(mascotaDto.getEdad());
		mascota.setSexo(mascotaDto.getSexo());
		mascota.setRaza(mascotaDto.getRaza());
		mascota.setColor(mascotaDto.getColor());
		mascota.setPeso(mascotaDto.getPeso());
		mascota.setDiscapacidad(mascotaDto.getDiscapacidad());
		mascota.setTipoAnimal(mascotaDto.getTipoAnimal());
		mascota.setImagenMascota(mascotaDto.getImagenMascota());
		
		MascotaVo mascotaActualizada = mascotaRepository.save(mascota);
		
		return mapearDto(mascotaActualizada);
	}

	@Override
	public void eliminarMascota(Long documentoUsuario, Long codigoMascota) {
		UsuarioVo usuario = usuarioRepository.findById(documentoUsuario).orElseThrow(() -> new ResourceNotFoudExeption("Usuario", "documento", documentoUsuario));
		MascotaVo mascota = mascotaRepository.findById(codigoMascota).orElseThrow(() -> new ResourceNotFoudExeption("Mascota", "codigo", codigoMascota));
		
		if(!mascota.getDueniomascota().getDocumentoUs().equals(usuario.getDocumentoUs())) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "la mascota no pertenese a este usuario");
		}
		
		mascotaRepository.delete(mascota);
	}
	
	@Override
	public MascotaPorNombreDto buscarMascotaPorNombre(String nombre) {
		
		MascotaVo mas = mascotaRepository.findByNombre(nombre).orElseThrow(() -> new ResourceNotFoudExeption("Mascota", "nombre", nombre));
		
		MascotaPorNombreDto mascotaNombreDto = new MascotaPorNombreDto();
		
		List<String[]> mascota = mascotaRepository.mascotaPorNombre(nombre);
		
		for (String[] datos : mascota) {
			mascotaNombreDto.setNombre(datos[0].toString());
			mascotaNombreDto.setEdad(datos[1].toString());
			mascotaNombreDto.setSexo(datos[2].toString());
			mascotaNombreDto.setRaza(datos[3].toString());
			mascotaNombreDto.setColor(datos[4].toString());
			mascotaNombreDto.setDiscapacidad(datos[5].toString());
			mascotaNombreDto.setTipoAnimal(datos[6].toString());
			mascotaNombreDto.setImagenMascota(datos[7].toString());
		}
		return mascotaNombreDto;
	}
	
	private MascotaDto mapearDto(MascotaVo mascota) {
		MascotaDto mascotaDTO = modelMapper.map(mascota, MascotaDto.class);
		return mascotaDTO;
	}
	
	private MascotaAswerDto mapearAnswerDto(MascotaVo mascota) {
		MascotaAswerDto mascotaAswerDto = modelMapper.map(mascota, MascotaAswerDto.class);
		return mascotaAswerDto;
	}
	
	private MascotaVo mapearEntidad(MascotaDto mascotaDto) {
		MascotaVo mascota = modelMapper.map(mascotaDto, MascotaVo.class);
		return mascota;
	}

}