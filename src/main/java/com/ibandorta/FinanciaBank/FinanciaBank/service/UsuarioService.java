package com.ibandorta.FinanciaBank.FinanciaBank.service;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Usuario;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodosLosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
