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

import com.pets1.app.dto.answers.UsuarioAnswerDto;
import com.pets1.app.dto.answers.UsuarioPorNombreDto;
import com.pets1.app.dto.answers.UsuarioyRolesDto;
import com.pets1.app.dto.entityData.UsuarioDto;
import com.pets1.app.service.IUsuarioService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class UsuarioRest {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<UsuarioAnswerDto> listaDeUsuarios(){
		return usuarioService.obtenerTodosLosUsuarios();
	}

	@GetMapping("/usuarios/{documento}")
	public ResponseEntity<UsuarioDto> buscarUsuarioPorDocumento(@PathVariable Long documento){
		return ResponseEntity.ok(usuarioService.buscarUsuarioPorDocumento(documento));
	}
	
	@GetMapping("/usuarios/{documento}/mascotas")			
	public ResponseEntity<UsuarioAnswerDto> buuscarUsuarioConMascotas(@PathVariable Long documento){
		return new ResponseEntity<>(usuarioService.buscarUsuarioConMascotas(documento), HttpStatus.OK);
	}
	
	@GetMapping("/usuarios/nombre/{nombre}")
	public UsuarioPorNombreDto buscarUsuarioPorNombre(@PathVariable String nombre) {
		UsuarioPorNombreDto usuario = usuarioService.buscarUsuarioPorNombre(nombre);
		return usuario;
	}
	
	@GetMapping("/usuarios/{documento}/rol")
	public UsuarioyRolesDto buscarUsuarioYRol(@PathVariable Long documento) {
		UsuarioyRolesDto usuario = usuarioService.buscarUsaurioConRol(documento);
		return usuario;
	}

	@PostMapping("/usuarios")
	public ResponseEntity<String> guardarUsuario(@Valid @RequestBody UsuarioDto usuarioDto){	
		usuarioService.guardarUsuario(usuarioDto);
		return new ResponseEntity<>("Usuario registrado con exito", HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/usuarios/{documento}")
	public ResponseEntity<String> actualizarUsuario(@PathVariable Long documento ,@Valid @RequestBody UsuarioDto usuarioDto){
		usuarioService.actualizarUsuario(usuarioDto, documento);
		return new ResponseEntity<>("Usuario Actualizado con exito", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/usuarios/{documento}/estado/{estadoUs}")
	public ResponseEntity<String> actualizarUsuarioEstado(@PathVariable Long documento ,@PathVariable int estadoUs){
		usuarioService.deshabilitarEstadoUsuario(estadoUs, documento);
		return new ResponseEntity<>("El estado del Usuario Actualizado con exito", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/usuarios/{documento}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable Long documento){
		usuarioService.eliminarUsuario(documento);
		return new ResponseEntity<String>("Usuario eliminado con exito", HttpStatus.OK);
	}
}