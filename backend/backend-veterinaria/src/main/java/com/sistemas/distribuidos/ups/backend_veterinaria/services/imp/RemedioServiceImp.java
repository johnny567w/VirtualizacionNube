package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Remedio;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.RemedioRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.RemedioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RemedioServiceImp implements RemedioService {

    private final RemedioRepository remedioRepository;

    public RemedioServiceImp(RemedioRepository remedioRepository) {
        this.remedioRepository = remedioRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Remedio> findAll() {
        return remedioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Remedio> findById(Long id) {
        return remedioRepository.findById(id);
    }

    @Transactional
    @Override
    public Remedio save(Remedio remedio) {
        return remedioRepository.save(remedio);
    }

    @Transactional
    @Override
    public Optional<Remedio> update(Long id, Remedio remedio) {
        Optional<Remedio> remedioOptional = remedioRepository.findById(id);
        if (remedioOptional.isPresent()){
            Remedio remedioDb = remedioOptional.get();
            remedioDb.setNombre(remedio.getNombre());
            remedioDb.setPrecio(remedio.getPrecio());
            return Optional.of(remedioRepository.save(remedioDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Remedio> deleteById(Long id) {
        Optional<Remedio> remedioOptional = remedioRepository.findById(id);
        if (remedioOptional.isPresent()){
            remedioRepository.deleteById(id);
            return remedioOptional;
        }
        return Optional.empty();
    }
}
