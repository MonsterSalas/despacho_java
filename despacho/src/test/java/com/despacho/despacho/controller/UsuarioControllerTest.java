package com.despacho.despacho.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.EntityModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.despacho.despacho.model.Usuario;
import com.despacho.despacho.service.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception {

        Usuario usuario1 = new Usuario();
        
        usuario1.setNombre("pruebas");
        usuario1.setCorreo("d@j.com");
        usuario1.setDireccion1("Prueba");
        usuario1.setPass("1234");
        usuario1.setRol("admin");
        
        Usuario usuario2 = new Usuario();
        
        usuario2.setNombre("pruebas2");
        usuario2.setCorreo("a@j.com");
        usuario2.setDireccion1("Prueba");
        usuario2.setPass("1234");
        usuario2.setRol("admin");
        
        usuario2.setId(21L);
        
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        
        when (usuarioServiceMock.getAllUsuario()).thenReturn (usuarios);
        
        // Act & Assert
        
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[0].nombre", Matchers.is("pruebas")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[1].nombre", Matchers.is("pruebas2")));
        }
}
