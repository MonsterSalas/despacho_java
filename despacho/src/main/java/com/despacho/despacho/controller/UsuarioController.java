package com.despacho.despacho.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import java.util.stream.Collectors;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> getAllUsuario(){
        List<Usuario> usuarios = usuarioService.getAllUsuario();

        
        List<EntityModel<Usuario>> usuarioResources = usuarios.stream()
            .map(usuario-> EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(usuario.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuario());
        CollectionModel<EntityModel<Usuario>> resources = CollectionModel.of(usuarioResources, linkTo.withRel("usuarios"));
        return resources;
    }
    @GetMapping("/{id}")
    public EntityModel<Usuario> getUsuarioByid(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByid(id);

        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuario()).withRel("all-usuarios"));
        } else {
            throw new UsuarioNotFounException("Student not found with id: " + id);
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
        String correo = usuario.getCorreo();
        String pass = usuario.getPass();
        Optional<Usuario> usuarioOptional = usuarioService.getUsuario(correo, pass);
        if (result.hasErrors()) {
            //Errores de validación, construye una respuesta con los mensajes de error
            StringBuilder errorMessage = new StringBuilder("Error de validación: ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            ResponseMessage errorResponse = new ResponseMessage(errorMessage.toString());
            EntityModel<ResponseMessage> entityModel = EntityModel.of(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createUsuario(usuario, result)).withSelfRel()));
        }
        if (usuarioOptional.isPresent()) {
            ResponseMessage errorResponse = new ResponseMessage("Este usuario ya se encuentra creado con el correo: " + correo);
            EntityModel<ResponseMessage> entityModel = EntityModel.of(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createUsuario(usuario, result)).withSelfRel()));
        } else {
            Usuario createdUsuario = usuarioService.createUsuario(usuario);
            EntityModel<Usuario> entityModel = EntityModel.of(createdUsuario,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(createdUsuario.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuario()).withRel("all-usuarios"));
            return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<EntityModel<ResponseMessage>> login(@RequestBody Usuario usuario) {
        String correo = usuario.getCorreo();
        String pass = usuario.getPass();
        // Obtener usuario por correo y contraseña
        Optional<Usuario> usuarioOptional = usuarioService.getUsuario(correo, pass);
        
        if(usuario.getCorreo() == null || usuario.getPass() == null ||
           usuario.getCorreo().isEmpty() || usuario.getPass().isEmpty())
        {            
            ResponseMessage errorResponse = new ResponseMessage("Los campos no pueden ser vacios");
            EntityModel<ResponseMessage> entityModel = EntityModel.of(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entityModel);
        }
        else
        {
            if (usuarioOptional.isPresent()) {
                ResponseMessage successResponse = new ResponseMessage("Usuario logeado exitosamente");
                EntityModel<ResponseMessage> entityModel = EntityModel.of(successResponse);
                entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuario()).withRel("all-usuarios"));
                return ResponseEntity.ok(entityModel);
            } else {
                ResponseMessage errorResponse = new ResponseMessage("Usuario o contraseña incorrectos");
                EntityModel<ResponseMessage> entityModel = EntityModel.of(errorResponse);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityModel);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            //Errores de validación, construye una respuesta con los mensajes de error
            StringBuilder errorMessage = new StringBuilder("Error de validación: ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            ResponseMessage errorResponse = new ResponseMessage(errorMessage.toString());
            EntityModel<ResponseMessage> entityModel = EntityModel.of(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateUsuario(id, usuario, result)).withSelfRel()));
        }
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        EntityModel<Usuario> entityModel = EntityModel.of(updatedUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(updatedUsuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuario()).withRel("all-usuarios"));
        return ResponseEntity.ok(entityModel);
    }
     
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    } 
    class ResponseMessage {
        private String message;
    
        public ResponseMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }
}
