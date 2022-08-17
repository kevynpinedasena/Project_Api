package com.pets1.app.rest;

import java.util.List;

import javax.validation.Valid;

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

import com.pets1.app.dto.answers.ClinicaAnswerDto;
import com.pets1.app.dto.answers.ClinicaPorNombreDto;
import com.pets1.app.dto.answers.ClinicayRolDto;
import com.pets1.app.dto.entityData.clinicaDto;
import com.pets1.app.service.IClinicaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class ClinicaRest {
	
	@Autowired
	private IClinicaService clinicaService;
	
	@GetMapping("/clinicas")
	public List<ClinicaAnswerDto> listaClinica(){
		return clinicaService.consultarListaDeClinicas();
	}
	
	@GetMapping("/clinicas/{nit}")
	public ResponseEntity<ClinicaAnswerDto> buscarClinicaID(@PathVariable Long nit){
		return ResponseEntity.ok(clinicaService.consultarClinicaPorId(nit));
	}
	
	@GetMapping("/clinicas/nombre/{nombre}")
	public ClinicaPorNombreDto buscarCliniicaPorNombre(@PathVariable String nombre) {
		ClinicaPorNombreDto clinica = clinicaService.buscarClinicaPorNombre(nombre);
		return clinica;
	}
	
	@GetMapping("/clinicas/{nit}/rol")
	public ClinicayRolDto buscarClinicaYRol(@PathVariable Long nit){
		return clinicaService.buscarClinicaYRol(nit);
	}
	
	@PostMapping("/clinicas")
	public ResponseEntity<String> guardarClinica(@Valid @RequestBody clinicaDto clinicaDto){	
		clinicaService.crearClinica(clinicaDto);
		return new ResponseEntity<>("Clinica registrada con exito", HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('CLINICA')")
	@PutMapping("/clinicas/{nit}")
	public ResponseEntity<String> actualizarClinica(@PathVariable Long nit ,@Valid @RequestBody clinicaDto clinicaDto){
		clinicaService.actualizarClinica(nit, clinicaDto);
		return new ResponseEntity<>("Clinica Actualizado con exito", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/clinicas/{nit}/estado/{estado}")
	public ResponseEntity<String> actualizarEstadoClinica(@PathVariable Long nit ,@PathVariable int estado){
		clinicaService.actualizarEstado(estado, nit);
		return new ResponseEntity<>("se actualizo el estado de la clinica con exito", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/clinicas/{nit}")
	public ResponseEntity<String> eliminarClinica(@PathVariable Long nit){
		clinicaService.eliminarClinica(nit);
		return new ResponseEntity<>("Clinica eliminado con exito", HttpStatus.OK);
	}
}