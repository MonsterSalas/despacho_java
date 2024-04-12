package com.despacho.despacho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.despacho.despacho.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByCorreoAndPass(String correo, String pass);
    
}
