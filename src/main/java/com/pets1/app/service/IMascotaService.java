package com.pets1.app.service;

import java.util.List;

import com.pets1.app.dto.answers.MascotaAswerDto;
import com.pets1.app.dto.answers.MascotaPorNombreDto;
import com.pets1.app.dto.entityData.MascotaDto;

public interface IMascotaService {

	void guardarMascota(Long documento, MascotaDto mascotaDto);
	
	List<MascotaAswerDto> obtenerMascotasDeUsuario(Long documentoUsuario);
	
	MascotaDto obtenerMascotaId(Long documentoUsuario, Long codigoMascota);
	
	MascotaDto actualizarMascota(Long documentoUsuario, Long codigoMascota, MascotaDto mascotaDto);
	
	void eliminarMascota(Long documentoUsuario, Long codigoMascota);
	
	MascotaPorNombreDto buscarMascotaPorNombre(String nombre);
	
}
