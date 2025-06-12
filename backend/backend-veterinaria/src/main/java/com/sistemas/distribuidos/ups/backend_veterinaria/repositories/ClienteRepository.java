package com.sistemas.distribuidos.ups.backend_veterinaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {   
}
