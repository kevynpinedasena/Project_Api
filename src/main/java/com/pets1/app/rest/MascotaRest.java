package com.pets1.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pets1.app.dto.answers.MascotaAswerDto;
import com.pets1.app.dto.answers.MascotaPorNombreDto;
import com.pets1.app.dto.entityData.MascotaDto;
import com.pets1.app.service.IMascotaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class MascotaRest {
	
	@Autowired
	private IMascotaService mascotaService;
	
	@GetMapping("/usuario/{documento}/mascota")
	public List<MascotaAswerDto> listaDeMascotasUsuario(@PathVariable Long documento){
		return mascotaService.obtenerMascotasDeUsuario(documento);
	}
	
	@GetMapping("/usuario/{documento}/mascota/{codigo}")
	public ResponseEntity<MascotaDto> buscarMascotaID(@PathVariable Long documento, @PathVariable Long codigo){
		MascotaDto mascotaDto = mascotaService.obtenerMascotaId(documento, codigo);	
		return new ResponseEntity<>(mascotaDto, HttpStatus.OK);
	}
	
	@GetMapping("/mascotas/{nombre}")
	public  MascotaPorNombreDto buscarMascotaPorNombre(@PathVariable String nombre) {
		MascotaPorNombreDto mascota = mascotaService.buscarMascotaPorNombre(nombre);
		return mascota;
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/usuario/{documento}/mascota")
	public ResponseEntity<String> guardarMascota(@PathVariable Long documento ,@RequestBody MascotaDto mascotaDto){
		mascotaService.guardarMascota(documento, mascotaDto);
		return new ResponseEntity<>("Mascota registrada con exito", HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/usuario/{documento}/mascota/{codigo}")
	public ResponseEntity<String> actualizarMascota(@PathVariable Long documento , @PathVariable Long codigo, @RequestBody MascotaDto mascotaDto){
		mascotaService.actualizarMascota(documento, codigo, mascotaDto);
		return new ResponseEntity<>("Mascota actualizada con exito" , HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/usuario/{documento}/mascota/{codigo}")
	public ResponseEntity<String> eliminarMascota(@PathVariable Long documento, @PathVariable Long codigo){
		mascotaService.eliminarMascota(documento, codigo);
		return new ResponseEntity<>("Mascota eliminada con exito", HttpStatus.OK);
	}
}