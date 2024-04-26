package com.despacho.despacho.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.despacho.despacho.model.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setNombre("pruebas");
        usuario.setCorreo("j@j.com");
        usuario.setDireccion1("Prueba");
        usuario.setPass("1234");
        usuario.setRol("admin");
        Usuario resultado = usuarioRepository.save(usuario);
        assertNotNull(resultado.getId());
        assertEquals("pruebas", resultado.getNombre());
    }
}