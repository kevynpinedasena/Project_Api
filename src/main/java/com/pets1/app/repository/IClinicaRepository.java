package com.pets1.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pets1.app.domain.ClinicaVo;

@Repository
public interface IClinicaRepository extends JpaRepository<ClinicaVo, Long>{
	
	@Query("SELECT c.nombre, c.direccion, c.telefono, c.correoCv, c.passwordCv, c.imagenclinica FROM ClinicaVo c WHERE c.nombre =:nombre")
	List<String[]> clinicaPorNombre(@Param("nombre") String nombre);
	
	@Query(value = "SELECT c.nit_cv,c.correo_cv,r.rol_id FROM clinica_veterinaria AS c INNER JOIN clinica_roles AS r ON c.nit_cv = r.clinica_nit WHERE c.nit_cv =:nit", nativeQuery = true)
	List<String[]> clinicayRoles(@Param("nit") Long nit);
	
	public Optional<ClinicaVo> findByCorreoCv(String correoCv);
	
	public Optional<ClinicaVo> findByNombreOrCorreoCv(String nombre, String correoCv);
	
	public Optional<ClinicaVo> findByNombre(String nombre);
	
	public Boolean existsByNombre(String nombre);
	
	public Boolean existsByCorreoCv(String correoCv);
}