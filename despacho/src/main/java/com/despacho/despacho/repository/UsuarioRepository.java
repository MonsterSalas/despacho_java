package com.despacho.despacho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.despacho.despacho.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
