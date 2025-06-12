package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.CitaRemedioDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Cita;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.CitaRemedio;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Remedio;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.CitaRemedioRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.CitaRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.RemedioRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.CitaRemedioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaRemedioImp implements CitaRemedioService {

    private final CitaRemedioRepository citaRemedioRepository;
    private final CitaRepository citaRepository;
    private final RemedioRepository remedioRepository;

    public CitaRemedioImp(CitaRemedioRepository citaRemedioRepository, CitaRepository citaRepository, RemedioRepository remedioRepository) {
        this.citaRemedioRepository = citaRemedioRepository;
        this.citaRepository = citaRepository;
        this.remedioRepository = remedioRepository;
    }

    @Override
    public List<CitaRemedio> findAll() {
        return citaRemedioRepository.findAll();
    }

    @Override
    public Optional<CitaRemedio> findById(Long id) {
        return citaRemedioRepository.findById(id);
    }

    @Override
    public CitaRemedio save(CitaRemedioDTO citaRemedioDTO) {

        CitaRemedio citaRemedio = new CitaRemedio();

        if (citaRemedioDTO.getId() != null) {
            citaRemedio.setId(citaRemedioDTO.getId());
        }

        citaRemedio.setDosis(citaRemedioDTO.getDosis());

        Optional<Cita> citaOptional  = citaRepository.findById(citaRemedioDTO.getCitaId());
        citaOptional.ifPresent(citaRemedio::setCita);

        Optional<Remedio> remedioOptional = remedioRepository.findById(citaRemedioDTO.getRemedioId());
        remedioOptional.ifPresent(citaRemedio::setRemedio);

        return citaRemedioRepository.save(citaRemedio);
    }

    @Override
    public Optional<CitaRemedio> update(Long id, CitaRemedioDTO citaRemedioDTO) {
        Optional<CitaRemedio> citaRemedioOptional = findById(id);
        if (citaRemedioOptional.isPresent()) {
            citaRemedioDTO.setId(id);
            return Optional.of(save(citaRemedioDTO));
        }
        return Optional.empty();
    }

    @Override
    public Optional<CitaRemedio> deleteById(Long id) {
        Optional<CitaRemedio> citaRemedioOptional = findById(id);
        if (citaRemedioOptional.isPresent()) {
            citaRemedioRepository.deleteById(id);
            return citaRemedioOptional;
        }
        return Optional.empty();
    }
}
