package com.pets1.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pets1.app.domain.RolVo;

public interface IRolRepository extends JpaRepository<RolVo, Long>{
	
	public Optional<RolVo> findByNombre(String nombre);
	
}