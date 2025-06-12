package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especie;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Raza;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.EspecieRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.RazaRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.EspecieRazaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieRazaServiceImp implements EspecieRazaService {

    private final EspecieRepository especieRepository;
    private final RazaRepository razaRepository;

    public EspecieRazaServiceImp(EspecieRepository especieRepository, RazaRepository razaRepository) {
        this.especieRepository = especieRepository;
        this.razaRepository = razaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Especie> findAllEspecies() {
        return especieRepository.findAll() ;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Raza> findAllRazas() {
        return razaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Raza> findAllRazasByEspecie(Long idEspecie) {
        Optional<Especie> especieOp = especieRepository.findById(idEspecie);
        return especieOp.map(razaRepository::findByEspecie).orElse(null);
    }

    @Transactional
    @Override
    public Especie saveEspecie(Especie especie) {
        return especieRepository.save(especie);
    }

    @Transactional
    @Override
    public Raza saveRaza(Raza raza) {
        return razaRepository.save(raza);
    }
}
