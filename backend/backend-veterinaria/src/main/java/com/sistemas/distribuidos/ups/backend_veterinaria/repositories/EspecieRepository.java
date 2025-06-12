package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Cliente;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
