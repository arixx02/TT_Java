package com.TalentoTech.miPrimerApi.Controller;

import com.TalentoTech.miPrimerApi.Model.Articulo;
import com.TalentoTech.miPrimerApi.Repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private ArticuloRepository repo;

    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return repo.save(articulo);
    }

    @GetMapping
    public List<Articulo> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> consultar(@PathVariable Long id) {
        return repo.findById(id)
                   .map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> modificar(@PathVariable Long id, @RequestBody Articulo datos) {
        return repo.findById(id).map(a -> {
            a.setNombre(datos.getNombre());
            a.setDescripcion(datos.getDescripcion());
            a.setPrecio(datos.getPrecio());
            return ResponseEntity.ok(repo.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
