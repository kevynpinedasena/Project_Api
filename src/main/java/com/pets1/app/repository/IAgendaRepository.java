package com.pets1.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pets1.app.domain.AgendaVo;

@Repository
public interface IAgendaRepository extends JpaRepository<AgendaVo, Long>{
	
	public List<AgendaVo> findByDocumentousDocumentoUs(Long documentous);
	
	public List<AgendaVo> findByDocumentovtDocumento(Long documentovt);
}