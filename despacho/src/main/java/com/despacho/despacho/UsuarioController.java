package com.despacho.despacho;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {


    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario(1, "Pepito Pica", "Cliente",
                Arrays.asList(new Direccion("Duoc 2023", "Santiago", "Huechuraba", "RM"),
                        new Direccion("Estero Nifre", "Santiago", "Quilicura", "RM"))));
        usuarios.add(new Usuario(2, "Carlos","Administrador",
                Arrays.asList(new Direccion("Av. los Libertadores 123", "Santiago", "Quilicura", "RM"),
                        new Direccion("Metropolitana", "Santiago", "Santiago", "RM"),
                        new Direccion("Costanera 123", "Viña del Mar", "Viña del mar", "Quinta Region"))));
        usuarios.add(new Usuario(3, "Jose","Cliente",
                Arrays.asList(new Direccion("Av. los Libertadores 123", "Santiago", "Quilicura", "RM"),
                        new Direccion("Metropolitana", "Santiago", "Santiago", "RM"),
                        new Direccion("Costanera 123", "Viña del Mar", "Viña del mar", "Quinta Region"))));
    }
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
//Obtener por ID
@GetMapping("/usuarios/{id}")
public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id) {
    Usuario usuarioEncontrado = null;
    for (Usuario usuario : usuarios) {
        if (usuario.getId() == id) {
            usuarioEncontrado = usuario;
            break; // Se encontró el usuario, salimos del bucle
        }
    }
    if (usuarioEncontrado != null) {
        return ResponseEntity.ok(usuarioEncontrado);
    } else {
        Errorres response = new Errorres("No se encontró ningún usuario con el ID '" + id + "'");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
// Obtener una usuario por su Rol
@GetMapping("/usuarios/rol/{rol}")
public ResponseEntity<?> obtenerUsuariosPorRol(@PathVariable String rol) {
    List<Usuario> usuariosConRol = new ArrayList<>();
    for (Usuario usuario : usuarios) {
        if (usuario.getRol().equals(rol)) {
            usuariosConRol.add(usuario);
        }
    }
    if (!usuariosConRol.isEmpty()) {
        return ResponseEntity.ok(usuariosConRol);
    } else {
        Errorres response = new Errorres("No se encontraron usuarios con el rol '" + rol + "'");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

}
