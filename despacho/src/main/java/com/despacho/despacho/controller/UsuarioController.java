package com.despacho.despacho.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;

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

    @PostMapping("/crear")
    public ResponseEntity<Object> createUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
        String correo = usuario.getCorreo();
        String pass = usuario.getPass();

        Optional<Usuario> usuarioOptional = usuarioService.getUsuario(correo, pass);
        if (result.hasErrors()) {
            // Si hay errores de validación, construye una respuesta con los mensajes de error
            StringBuilder errorMessage = new StringBuilder("Error de validación: ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuario ya se encuentra creado");
        } else {
            
            Usuario createdUsuario = usuarioService.createUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        String correo = usuario.getCorreo();
        String pass = usuario.getPass();
        // Obtener usuario por correo y contraseña
        Optional<Usuario> usuarioOptional = usuarioService.getUsuario(correo, pass);
         if(usuario.getCorreo() == null || usuario.getPass() == null ||
         usuario.getCorreo().isEmpty() || usuario.getPass().isEmpty())
         {            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos no pueden ser vacios");
         }
         else
         {
            if (usuarioOptional.isPresent()) {
                return ResponseEntity.ok("Usuario logeado exitosamente");
            } else {
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o contraseña incorrectos");
            }
         }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores de validación, construye una respuesta con los mensajes de error
            StringBuilder errorMessage = new StringBuilder("Error de validación: ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }

        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    } 
}
