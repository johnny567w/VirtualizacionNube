package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especie;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Raza;

import java.util.List;

public interface EspecieRazaService {

    List<Especie> findAllEspecies();
    List<Raza> findAllRazas();

    List<Raza> findAllRazasByEspecie(Long idEspecie);

    Especie saveEspecie(Especie especie);
    Raza saveRaza(Raza raza);
}
