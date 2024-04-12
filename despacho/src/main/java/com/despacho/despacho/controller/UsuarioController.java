package com.despacho.despacho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.despacho.despacho.model.Usuario;
import com.despacho.despacho.service.UsuarioService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuario(){
        return usuarioService.getAllUsuario();
    }
            
    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioByid(@PathVariable Long id) {
        return usuarioService.getUsuarioByid(id);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        String correo = usuario.getCorreo();
        String pass = usuario.getPass();

        // Obtener usuario por correo y contraseña
        Optional<Usuario> usuarioOptional = usuarioService.getUsuario(correo, pass);

        if (usuarioOptional.isPresent()) {
            return "Usuario logeado exitosamente";
        } else {
            return "Usuario o contraseña incorrectos";
        }
    }
        
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id,@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id,usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    } 
}
