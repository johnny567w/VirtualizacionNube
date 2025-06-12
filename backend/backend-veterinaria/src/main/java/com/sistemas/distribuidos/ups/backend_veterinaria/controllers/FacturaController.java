package com.sistemas.distribuidos.ups.backend_veterinaria.controllers;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.FacturaDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.dto.FacturaDetalleDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Factura;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<Factura> findAll(){
        return facturaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Factura> findById(@PathVariable Long id){
        return facturaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody FacturaDTO facturaDTO){
        Factura factura = facturaService.save(facturaDTO);
        return ResponseEntity.ok(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody FacturaDTO facturaDTO, @PathVariable Long id){
        Optional<Factura> facturaOptional = facturaService.update(id, facturaDTO);
        if (facturaOptional.isPresent()) {
            return ResponseEntity.ok(facturaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Factura> facturaOptional = facturaService.deleteById(id);
        if (facturaOptional.isPresent()) {
            return ResponseEntity.ok(facturaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/add-detalle/{id}")
    public ResponseEntity<?> addFacturaDetalle(@RequestBody FacturaDetalleDTO facturaDetalleDTO, @PathVariable Long id ){
        System.out.println(id);
        Optional<Factura> facturaOptional =  facturaService.addFacturaDetalle(id, facturaDetalleDTO);
        if (facturaOptional.isPresent()) {
            return ResponseEntity.ok(facturaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/remove-detalle/{id}/{id_detalle}")
    public ResponseEntity<?> removeFacturaDetalle(@PathVariable Long id,  @PathVariable Long id_detalle ){
        Optional <Factura> facturaOptional =  facturaService.removeFacturaDetalle(id, id_detalle);
        if (facturaOptional.isPresent()) {
            return ResponseEntity.ok(facturaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/final/{id}")
    public ResponseEntity<?> findFacturaFinal(@PathVariable Long id){
        Optional<Factura> facturaOptional =  facturaService.findFacturaFinal(id);
        if (facturaOptional.isPresent()) {
            return ResponseEntity.ok(facturaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


}
