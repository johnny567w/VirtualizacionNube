package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaService {

    List<Mascota> findAll();
    Optional<Mascota> findById(Long id);
    Mascota save(Mascota mascota);
    Optional<Mascota> update(Long id, Mascota mascota);
    Optional<Mascota> deleteById(Long id);

}
