package com.despacho.despacho.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.despacho.despacho.model.Usuario;
import com.despacho.despacho.service.UsuarioServiceImpl;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    @Mock
    private UsuarioRepository usuarioRepositoryMock;

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

    @Test
    public void testGetUsuario_Success() {
        // Datos de prueba
        String correo = "prueba@prueba.com";
        String pass = "prueba";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setPass(pass);

        // Simulación del comportamiento del repositorio
        when(usuarioRepositoryMock.findByCorreoAndPass(correo, pass)).thenReturn(Optional.of(usuario));

        // Llamar al método de servicio que se está probando
        Optional<Usuario> usuarioOptional = usuarioService.getUsuario(correo, pass);

        // Verificar que el usuario devuelto coincida con el usuario simulado
        assertEquals(Optional.of(usuario), usuarioOptional);
    }

    @Test
    public void testGetUsuario_NotFound() {
        // Datos de prueba
        String correo = "prueba@prueba.com";
        String pass = "prueba";

        // Simulación del comportamiento del repositorio (usuario no encontrado)
        when(usuarioRepositoryMock.findByCorreoAndPass(correo, pass)).thenReturn(Optional.empty());

        // Llamar al método de servicio que se está probando
        Optional<Usuario> usuarioOptional = usuarioService.getUsuario(correo, pass);

        // Verificar que el usuario devuelto sea un Optional vacío
        assertEquals(Optional.empty(), usuarioOptional);
    }
}