package com.sistemas.distribuidos.ups.backend_veterinaria.controllers;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Remedio;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.RemedioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/remedios")
public class RemedioController {

    private final RemedioService remedioService;

    public RemedioController(RemedioService remedioService) {
        this.remedioService = remedioService;
    }

    @GetMapping
    public List<Remedio> list() {
        return remedioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Remedio> remedioOptional = remedioService.findById(id);
        if (remedioOptional.isPresent()) {
            return ResponseEntity.ok(remedioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Remedio remedio) {
        Remedio remedioSave = remedioService.save(remedio);
        return ResponseEntity.ok(remedioSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Remedio remedio, @PathVariable Long id){
        Optional<Remedio> remedioOptional = remedioService.update(id, remedio);
        if (remedioOptional.isPresent()) {
            return ResponseEntity.ok(remedioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Remedio> remedioOptional = remedioService.deleteById(id);
        if (remedioOptional.isPresent()) {
            return ResponseEntity.ok(remedioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


}
