package com.pets1.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pets1.app.domain.keyTemporalVo;

@Repository
public interface IKeyTemporalRepository extends JpaRepository<keyTemporalVo, String>{

}
