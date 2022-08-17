package com.pets1.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pets1.app.domain.MascotaVo;

@Repository
public interface IMascotaRepository extends JpaRepository<MascotaVo, Long>{
	
	@Query("SELECT m.nombre, m.edad, m.sexo, m.raza, m.color, m.discapacidad, m.tipoAnimal, m.imagenMascota FROM MascotaVo m WHERE m.nombre =:nombre")
	List<String[]> mascotaPorNombre(@Param("nombre") String nombre);
	
	public List<MascotaVo> findByDueniomascotaDocumentoUs(Long usuarioDocumento);
	
	public Optional<MascotaVo> findByNombre(String nombre);

}