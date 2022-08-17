package com.pets1.app.seguridad;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pets1.app.domain.RolVo;
import com.pets1.app.domain.VeterinarioVo;
import com.pets1.app.repository.IVeterinarioRepository;

@Service
public class CustomVeterinarioDetailsService implements UserDetailsService{

	@Autowired
	private IVeterinarioRepository veterinarioRepository;

	@Override
	public UserDetails loadUserByUsername(String nombreOrCorreo) throws UsernameNotFoundException {
		
		VeterinarioVo veterinario = veterinarioRepository.findByNombreOrCorreo(nombreOrCorreo, nombreOrCorreo)
				.orElseThrow(() -> new UsernameNotFoundException("Veterinario no Encontrado"));
		
		return new User(veterinario.getCorreo(), veterinario.getPassword(), mapearRoles(veterinario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearRoles(Set<RolVo> roles){		
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
	
}
