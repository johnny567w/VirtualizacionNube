package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import java.util.List;
import java.util.Optional;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Cliente;

public interface ClienteService {

    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    Optional<Cliente> update(Long id, Cliente cliente);
    Optional<Cliente> deleteById(Long id);

}
