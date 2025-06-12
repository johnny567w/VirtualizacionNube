package com.sistemas.distribuidos.ups.backend_veterinaria.services;


import com.sistemas.distribuidos.ups.backend_veterinaria.dto.VeterinarioDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especialidad;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioService {

    List<Veterinario> findAll();
    Optional<Veterinario> findById(Long id);
    Veterinario save(VeterinarioDTO veterinarioDTO);
    Optional<Veterinario> update(Long id, VeterinarioDTO veterinarioDTO);
    Optional<Veterinario> deleteById(Long id);

    Especialidad saveEspecialidad(Especialidad especialidad);
    List<Especialidad> findAllEspecialidades();

    Optional<Veterinario> addEspecialidad(Long id, Long idEspecialidad);

}
