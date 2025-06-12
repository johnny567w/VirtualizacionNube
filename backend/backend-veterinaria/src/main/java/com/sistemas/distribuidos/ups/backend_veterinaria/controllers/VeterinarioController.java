package com.sistemas.distribuidos.ups.backend_veterinaria.controllers;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.VeterinarioDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especialidad;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Mensaje;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Veterinario;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioServicio;

    public VeterinarioController(VeterinarioService veterinarioServicio) {
        this.veterinarioServicio = veterinarioServicio;
    }

    @GetMapping
    public List<Veterinario> list() {
        return veterinarioServicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Veterinario> veterinarioOptional = veterinarioServicio.findById(id);
        if (veterinarioOptional.isPresent()) {
            return ResponseEntity.ok(veterinarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VeterinarioDTO veterinarioDTO) {
        Veterinario veterinarioSave = veterinarioServicio.save(veterinarioDTO);
        return ResponseEntity.ok(veterinarioSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody VeterinarioDTO veterinarioDTO, @PathVariable Long id){
        Optional<Veterinario> veterinarioOptional = veterinarioServicio.update(id, veterinarioDTO);
        if (veterinarioOptional.isPresent()) {
            return ResponseEntity.ok(veterinarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Veterinario> veterinarioOptional = veterinarioServicio.deleteById(id);
        if (veterinarioOptional.isPresent()) {
            return ResponseEntity.ok(new Mensaje("El veterinario con id: " + id + " ha sido eliminado correctamente."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("El veterinario con id: " + id + " no existe."));
    }

    @GetMapping("/especialidades")
    public List<Especialidad> findAllEspecialidades() {
        return veterinarioServicio.findAllEspecialidades();
    }

    @PostMapping("/especialidades")
    public Especialidad saveEspecialidad(@RequestBody Especialidad especialidad) {
        return veterinarioServicio.saveEspecialidad(especialidad);
    }

    @PutMapping("/especialidades/{idVeterinario}/{idEspecialidad}")
    public ResponseEntity<?> addEspecialidad(@PathVariable Long idVeterinario, @PathVariable Long idEspecialidad) {
        Optional<Veterinario> veterinarioOp = veterinarioServicio.addEspecialidad(idVeterinario, idEspecialidad);
        if (veterinarioOp.isPresent()) {
            return ResponseEntity.ok(veterinarioOp.get());
        }
        return ResponseEntity.notFound().build();
    }


}
