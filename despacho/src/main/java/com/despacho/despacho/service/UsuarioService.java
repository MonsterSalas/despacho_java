package com.despacho.despacho.service;

import com.despacho.despacho.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAllUsuario();
    Optional<Usuario> getUsuarioByid(Long id);
    Optional<Usuario> getUsuario(String usuario,String pass);

    Usuario createUsuario (Usuario usuario);

    Usuario updateUsuario (Long id, Usuario usuario);

    void deleteUsuario (Long id);


}
