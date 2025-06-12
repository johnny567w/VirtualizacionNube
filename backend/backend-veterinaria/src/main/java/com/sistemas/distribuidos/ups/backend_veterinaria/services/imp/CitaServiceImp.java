package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.CitaDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Cita;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.EstadoCita;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Mascota;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Veterinario;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.CitaRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.EstadoCitaRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.MascotaRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.VeterinarioRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.CitaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImp implements CitaService {

    private final CitaRepository citaRepository;
    private final EstadoCitaRepository estadoCitaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaServiceImp(CitaRepository citaRepository, EstadoCitaRepository estadoCitaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.estadoCitaRepository = estadoCitaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstadoCita> findAllEstadosCita() {
        return estadoCitaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<EstadoCita> findEstadoCitaById(Long id) {
        return estadoCitaRepository.findById(id);
    }

    @Override
    public List<Cita> findAllByMascotaId(Long idMascota) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(idMascota);
        if (mascotaOptional.isPresent()){
            Mascota mascota = mascotaOptional.get();
            return citaRepository.findAllByMascota(mascota);
        }
        return List.of();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    @Transactional
    @Override
    public Cita save(CitaDTO citaDto) {
        Cita citaSave = new Cita();
        citaSave.setSintomas(citaDto.getSintomas());
        citaSave.setPrecio(citaDto.getPrecio());
        citaSave.setFecha(citaDto.getFecha());

        Optional<Mascota> mascota = mascotaRepository.findById(citaDto.getMascotaId());
        mascota.ifPresent(citaSave::setMascota);

        Optional<EstadoCita> estadoCita = estadoCitaRepository.findById(citaDto.getEstadoCitaId());
        estadoCita.ifPresent(citaSave::setEstado);

        List<Veterinario> veterinarios = veterinarioRepository.findAllById(citaDto.getVeterinarios());
        citaSave.setVeterinarios(veterinarios);

        return citaRepository.save(citaSave);
    }

    @Transactional
    @Override
    public Optional<Cita> update(Long id, CitaDTO citaDto) {
        Optional<Cita> citaOptional = findById(id);
        if (citaOptional.isPresent()){
            Cita citaUpdate = citaOptional.get();

            citaUpdate.setFecha(citaDto.getFecha());
            citaUpdate.setPrecio(citaDto.getPrecio());
            citaUpdate.setSintomas(citaDto.getSintomas());

            Optional<EstadoCita> estadoCita = estadoCitaRepository.findById(citaDto.getEstadoCitaId());
            estadoCita.ifPresent(citaUpdate::setEstado);

            Optional<Mascota> mascota = mascotaRepository.findById(citaDto.getMascotaId());
            mascota.ifPresent(citaUpdate::setMascota);

            List<Veterinario> veterinarios = veterinarioRepository.findAllById(citaDto.getVeterinarios());
            citaUpdate.setVeterinarios(veterinarios);

            return Optional.of(citaRepository.save(citaUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Cita> deleteById(Long id) {
        Optional<Cita> citaOptional = findById(id);
        if (citaOptional.isPresent()){
            citaRepository.deleteById(id);
            return citaOptional;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Cita> changeEstadoCita(Long id, Long idEstadoCita) {
        Optional<Cita> citaOptional = findById(id);
        if (citaOptional.isPresent()){
            Cita citaUpdate = citaOptional.get();

            Optional<EstadoCita> estadoCitaOptional = findEstadoCitaById(idEstadoCita);
            if (estadoCitaOptional.isPresent()){
                EstadoCita estadoCita = estadoCitaOptional.get();
                citaUpdate.setEstado(estadoCita);
                return Optional.of(citaRepository.save(citaUpdate));
            }
            return Optional.empty();
        }
        return Optional.empty();
    }
}
