package com.despacho.despacho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.despacho.despacho.model.Usuario;
import com.despacho.despacho.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> getAllUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioByid(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) {
        if( usuarioRepository.existsById(id)){
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }else{
            return null;
        }
    }
    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> getUsuario(String usuario, String pass) {
        return usuarioRepository.findAll();
    }
    
}
