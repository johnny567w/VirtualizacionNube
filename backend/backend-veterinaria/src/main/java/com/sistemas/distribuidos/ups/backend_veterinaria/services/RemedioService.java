package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Remedio;

import java.util.List;
import java.util.Optional;

public interface RemedioService {

    List<Remedio> findAll();
    Optional<Remedio> findById(Long id);
    Remedio save(Remedio remedio);
    Optional<Remedio> update(Long id, Remedio remedio);
    Optional<Remedio> deleteById(Long id);
}
