package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.FacturaDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.dto.FacturaDetalleDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Factura;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.FacturaDetalle;

import java.util.List;
import java.util.Optional;

public interface FacturaService {

    List<Factura> findAll();
    Optional<Factura> findById(Long id);
    Factura save(FacturaDTO facturaDTO);
    Optional<Factura> update(Long id, FacturaDTO facturaDTO);
    Optional<Factura> deleteById(Long id);

    Optional<Factura> findFacturaFinal(Long id);

    Optional<Factura> addFacturaDetalle(Long id, FacturaDetalleDTO facturaDetalleDTO);
    Optional<Factura> removeFacturaDetalle(Long id, Long idFacturaDetalle);

    List<FacturaDetalle> findAllFacturaDetalle();
    Optional<FacturaDetalle> findByIdFacturaDetalle(Long id);
    // FacturaDetalle saveFacturaDetalle(FacturaDetalle facturaDetalle);
    // Optional<FacturaDetalle> updateFacturaDetalle(Long id, Factura facturaDetalle);
    // Optional<FacturaDetalle> deleteByIdFacturaDetalle(Long id);
}
