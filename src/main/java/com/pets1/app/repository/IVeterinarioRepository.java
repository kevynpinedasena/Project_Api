package com.pets1.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pets1.app.domain.VeterinarioVo;

@Repository
public interface IVeterinarioRepository extends JpaRepository<VeterinarioVo, Long>{
	
	@Query("SELECT v.nombre, v.apellidos, v.sexovt, v.telefono, v.correo, v.especialidad, v.password, v.imagenVete FROM VeterinarioVo v WHERE v.nombre =:nombre")
	List<String[]> veterinarioPorNombre(@Param("nombre") String nombre);
	
	@Query(value = "SELECT v.documento_vt,v.correo_vt,r.rol_id FROM veterinario AS v INNER JOIN veterinarios_roles AS r ON v.documento_vt = r.veterinario_doc WHERE v.documento_vt =:documento", nativeQuery = true)
	List<String[]> veterinarioyRoles(@Param("documento") Long documento);
	
	public List<VeterinarioVo> findByclinicaNit(Long nitClinica);

	public Optional<VeterinarioVo> findByCorreo(String correo);
	
	public Optional<VeterinarioVo> findByNombreOrCorreo(String nombre, String correo);
	
	public Optional<VeterinarioVo> findByNombre(String nombre);
	
	public Boolean existsByNombre(String nombre);
	
	public Boolean existsByCorreo(String correo);
}