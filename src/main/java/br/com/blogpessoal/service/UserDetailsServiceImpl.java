package br.com.blogpessoal.service;


import java.util.Optional;

import br.com.blogpessoal.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.com.blogpessoal.model.UsuarioModel;
import br.com.blogpessoal.repository.UsuarioRepository;

import javax.swing.text.html.Option;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByUsuario(username);

        if (usuarioOpt.isPresent())
            return new UserDetailsImpl(usuarioOpt.get());
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }
}