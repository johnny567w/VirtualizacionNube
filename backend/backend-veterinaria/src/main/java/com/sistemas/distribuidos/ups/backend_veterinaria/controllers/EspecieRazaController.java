package com.sistemas.distribuidos.ups.backend_veterinaria.controllers;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especie;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Raza;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.EspecieRazaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especies-razas")
public class EspecieRazaController {

    private final EspecieRazaService especieRazaService;

    public EspecieRazaController(EspecieRazaService especieRazaService) {
        this.especieRazaService = especieRazaService;
    }

    @GetMapping("/especies")
    public List<Especie> findAllEspecies() {
        return especieRazaService.findAllEspecies();
    }

    @GetMapping("/razas")
    public List<Raza> findAllRazas() {
        return especieRazaService.findAllRazas();
    }

    @GetMapping("/raza-por-especie/{id}")
    public List<Raza> findAllRazasByEspecie(@PathVariable Long id) {
        return especieRazaService.findAllRazasByEspecie(id);
    }

    @PostMapping("/especies")
    public Especie saveEspecie(@RequestBody Especie especie) {
        System.out.println("Recibido: id=" + especie.getId() + ", nombre=" + especie.getNombre());
        return especieRazaService.saveEspecie(especie);
    }


    @PostMapping("/razas")
    public Raza saveRaza(@RequestBody Raza raza) {
        return especieRazaService.saveRaza(raza);
    }
}
