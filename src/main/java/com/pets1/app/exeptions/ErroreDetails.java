package com.pets1.app.exeptions;

import java.util.Date;

public class ErroreDetails {
	
	private Date marcaDeTiemppo;
	private String mensaje;
	private String detalles;
	
	public ErroreDetails(Date marcaDeTiemppo, String mensaje, String detalles) {
		super();
		this.marcaDeTiemppo = marcaDeTiemppo;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}

	public Date getMarcaDeTiemppo() {
		return marcaDeTiemppo;
	}

	public void setMarcaDeTiemppo(Date marcaDeTiemppo) {
		this.marcaDeTiemppo = marcaDeTiemppo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
}