package com.pets1.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pets1.app.dto.entityData.LoginDto;
import com.pets1.app.seguridad.JwtAuthResponceDto;
import com.pets1.app.seguridad.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"*"})
public class AuthRest {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@PostMapping("/iniciarSesion")
	public ResponseEntity<JwtAuthResponceDto> authenticateUser(@RequestBody LoginDto loginDto){
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getNombreoCorreo(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//obtener el token de jwtProvider
		String token = tokenProvider.generarToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthResponceDto(token));
	}
}