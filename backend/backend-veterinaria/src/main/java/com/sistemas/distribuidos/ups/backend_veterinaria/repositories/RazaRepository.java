package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especie;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Raza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RazaRepository extends JpaRepository<Raza, Long> {
    List<Raza> findByEspecie(Especie especie);
}
