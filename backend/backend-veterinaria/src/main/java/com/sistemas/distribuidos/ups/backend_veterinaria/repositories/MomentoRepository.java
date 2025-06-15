package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Momento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MomentoRepository extends JpaRepository<Momento, Long> {
}
