package com.sistemas.distribuidos.ups.backend_veterinaria.controllers;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.CitaRemedioDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.CitaRemedio;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Mensaje;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Veterinario;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.CitaRemedioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas-remedios")
public class CitaRemedioController {

    private final CitaRemedioService citaRemedioService;

    public CitaRemedioController(CitaRemedioService citaRemedioService) {
        this.citaRemedioService = citaRemedioService;
    }

    @GetMapping
    public List<CitaRemedio> list(){
        return citaRemedioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return citaRemedioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CitaRemedioDTO citaRemedioDTO){
        CitaRemedio citaRemedio = citaRemedioService.save(citaRemedioDTO);
        return ResponseEntity.ok(citaRemedio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CitaRemedioDTO citaRemedioDTO, @PathVariable Long id){
        return citaRemedioService.update(id, citaRemedioDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<CitaRemedio> citaRemedioOptional = citaRemedioService.deleteById(id);
        if (citaRemedioOptional.isPresent()) {
            return ResponseEntity.ok(new Mensaje("La cita remedio con id: " + id + " ha sido eliminado correctamente."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("La cita remedio con id: " + id + " no existe."));
    }

}
