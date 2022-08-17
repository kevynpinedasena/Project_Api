package com.pets1.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pets1.app.domain.HorariosVo;

public interface IHorariosRepository extends JpaRepository<HorariosVo, Long>{

	public List<HorariosVo> findByDocumentovtDocumento(Long documentovt);
	
	public List<HorariosVo> findBynitCliNit(Long nitCli);
}
