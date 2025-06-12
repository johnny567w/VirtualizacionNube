package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
}
