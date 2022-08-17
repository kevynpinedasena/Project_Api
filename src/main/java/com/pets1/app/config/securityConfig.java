package com.pets1.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pets1.app.seguridad.CustomClinicaDetailsService;
import com.pets1.app.seguridad.CustomUserDetailsService;
import com.pets1.app.seguridad.CustomVeterinarioDetailsService;
import com.pets1.app.seguridad.JwtAutenticationEntryPoint;
import com.pets1.app.seguridad.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class securityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired 
	private CustomClinicaDetailsService clinicaDetailsService;
	
	@Autowired
	private CustomVeterinarioDetailsService veterinarioDetailsService;
	
	@Autowired
	private JwtAutenticationEntryPoint autenticationEntryPoint;
	
	@Bean
	public JwtAuthenticationFilter authenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().init(http);
		http.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(autenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll()
		.antMatchers("/api/auth/**").permitAll()
		.antMatchers(HttpMethod.POST ,"/api/usuarios/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/clinicas/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/generarkey/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/actualizar/contrasena/**").permitAll()
		.anyRequest()
		.authenticated();
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(clinicaDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(veterinarioDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}