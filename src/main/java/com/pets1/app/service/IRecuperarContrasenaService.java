package com.pets1.app.service;

import com.pets1.app.dto.answers.KeyTemporalAnswerDto;
import com.pets1.app.dto.entityData.CambiarCotrasenaDto;
import com.pets1.app.dto.entityData.RecuperarContrasenaDto;

public interface IRecuperarContrasenaService {
	
	KeyTemporalAnswerDto generarKey(RecuperarContrasenaDto correo);
	
	void cambiarContrasenaKey(CambiarCotrasenaDto cambiarCotrasenaDto, String key);

}
