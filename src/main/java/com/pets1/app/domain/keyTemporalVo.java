package com.pets1.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keytemporal")
public class keyTemporalVo {
	
	@Id
	@Column(name = "keyusu", nullable = false)
	private String key;
	
	@Column(name = "correo", nullable = false)
	private String correo;
	
	public keyTemporalVo() {
		super();
	}

	public keyTemporalVo(String key, String correo) {
		super();
		this.key = key;
		this.correo = correo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}	
	
}
