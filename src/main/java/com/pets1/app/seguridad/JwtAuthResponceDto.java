package com.pets1.app.seguridad;

public class JwtAuthResponceDto {
	
	private String tokenDeAcceso;
	private String tipoDeToken;
	
	public JwtAuthResponceDto(String tokenDeAcceso) {
		super();
		this.tokenDeAcceso = tokenDeAcceso;
	}
	
	public JwtAuthResponceDto(String tokenDeAcceso, String tipoDeToken) {
		super();
		this.tokenDeAcceso = tokenDeAcceso;
		this.tipoDeToken = tipoDeToken;
	}
	
	public String getTokenDeAcceso() {
		return tokenDeAcceso;
	}
	
	public void setTokenDeAcceso(String tokenDeAcceso) {
		this.tokenDeAcceso = tokenDeAcceso;
	}
	
	public String getTipoDeToken() {
		return tipoDeToken;
	}
	
	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}
	
}
