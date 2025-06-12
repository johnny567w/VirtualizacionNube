package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Long> {
}
