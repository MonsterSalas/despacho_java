package com.despacho.despacho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.despacho.despacho.model.Errorres;
import com.despacho.despacho.model.Usuario;
import com.despacho.despacho.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UsuarioController {


    @RestController
    @RequestMapping("/usuarios")
    public class StudentController {
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
        
        @GetMapping("/login")
        public Optional<Usuario> getUsuario(@PathVariable String usuario,String pass){
            return usuarioService.getUsuario(usuario,pass);
        }

        @PostMapping
        public Usuario createUsuario(@RequestBody Usuario usuario) {
            return usuarioService.createUsuario(usuario);
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
    
}
