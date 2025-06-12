package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Long> {
}
