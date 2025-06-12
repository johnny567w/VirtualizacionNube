package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Mascota;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.MascotaRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.MascotaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImp implements MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaServiceImp(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> findById(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public Optional<Mascota> update(Long id, Mascota mascota) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        if (mascotaOptional.isPresent()){
            Mascota mascotaUpdate = mascotaOptional.get();

            mascotaUpdate.setNombre(mascota.getNombre());
            mascotaUpdate.setFechaNacimiento(mascota.getFechaNacimiento());
            mascotaUpdate.setCliente(mascota.getCliente());
            mascotaUpdate.setRaza(mascota.getRaza());
            return Optional.of(mascotaRepository.save(mascotaUpdate));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Mascota> deleteById(Long id) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        if (mascotaOptional.isPresent()){
            mascotaRepository.deleteById(id);
            return mascotaOptional;
        }
        return Optional.empty();
    }
}
