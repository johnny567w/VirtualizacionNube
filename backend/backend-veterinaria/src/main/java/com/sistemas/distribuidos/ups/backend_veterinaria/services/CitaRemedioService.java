package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.CitaRemedioDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.CitaRemedio;
import java.util.List;
import java.util.Optional;

public interface CitaRemedioService  {

    List<CitaRemedio> findAll();
    Optional<CitaRemedio> findById(Long id);
    CitaRemedio save(CitaRemedioDTO citaRemedioDTO);
    Optional<CitaRemedio> update(Long id, CitaRemedioDTO citaRemedioDTO);
    Optional<CitaRemedio> deleteById(Long id);




}
