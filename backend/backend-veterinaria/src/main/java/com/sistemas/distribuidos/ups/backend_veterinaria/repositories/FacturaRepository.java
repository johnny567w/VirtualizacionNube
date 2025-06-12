package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
