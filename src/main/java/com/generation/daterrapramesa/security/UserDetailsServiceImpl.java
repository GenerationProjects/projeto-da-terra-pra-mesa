package com.generation.daterrapramesa.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.daterrapramesa.model.Usuario;
import com.generation.daterrapramesa.repository.UsuarioRepository;

@Service //indica que é uma classe de serviço
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired //injeção da interface (acessa todos os métodos)
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if(usuario.isPresent())
			return new UserDetailsImpl(usuario.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}
	
	
	
	
}
