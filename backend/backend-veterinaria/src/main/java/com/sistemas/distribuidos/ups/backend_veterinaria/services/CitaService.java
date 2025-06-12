package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.CitaDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Cita;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.EstadoCita;

import java.util.List;
import java.util.Optional;

public interface CitaService {

    List<EstadoCita> findAllEstadosCita();
    Optional<EstadoCita> findEstadoCitaById(Long id);

    List<Cita> findAll();
    List<Cita> findAllByMascotaId(Long idMascota);
    Optional<Cita> findById(Long id);
    Cita save(CitaDTO citaDTO);
    Optional<Cita> update(Long id, CitaDTO citaDTO);
    Optional<Cita> deleteById(Long id);


    Optional<Cita> changeEstadoCita(Long id, Long idEstadoCita);
}

