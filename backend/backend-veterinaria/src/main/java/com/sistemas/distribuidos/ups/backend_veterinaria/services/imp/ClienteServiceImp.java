package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Cliente;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.ClienteRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImp(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    @Override
    public Optional<Cliente> update(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = findById(id);
        if (clienteOptional.isPresent()) {
            Cliente clienteUpdate = clienteOptional.get();

            clienteUpdate.setNombre(cliente.getNombre());
            clienteUpdate.setCedula(cliente.getCedula());
            clienteUpdate.setTelefono(cliente.getTelefono());
            clienteUpdate.setCorreo(cliente.getCorreo());
            return Optional.of(save(clienteUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Cliente> deleteById(Long id) {
        Optional<Cliente> clienteOptional = findById(id);
        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
            return clienteOptional;
        }
        return Optional.empty();
    }
}
