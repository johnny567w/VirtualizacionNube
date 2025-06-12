package com.sistemas.distribuidos.ups.backend_veterinaria.controllers;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Mascota;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> list() {
        return mascotaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Mascota> mascotaOptional = mascotaService.findById(id);
        if (mascotaOptional.isPresent()) {
            return ResponseEntity.ok(mascotaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Mascota mascota) {
        Mascota mascotaSave = mascotaService.save(mascota);
        return ResponseEntity.ok(mascotaSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Mascota mascota, @PathVariable Long id){
        Optional<Mascota> mascotaOptional = mascotaService.update(id, mascota);
        if (mascotaOptional.isPresent()) {
            return ResponseEntity.ok(mascotaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Mascota> mascotaOptional = mascotaService.deleteById(id);
        if (mascotaOptional.isPresent()) {
            return ResponseEntity.ok(mascotaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
