package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.VeterinarioDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Especialidad;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.Veterinario;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.EspecialidadRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.VeterinarioRepository;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.VeterinarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImp implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    private final EspecialidadRepository especialidadRepository;

    public VeterinarioServiceImp(VeterinarioRepository veterinarioRepository, EspecialidadRepository especialidadRepository) {
        this.veterinarioRepository = veterinarioRepository;
        this.especialidadRepository = especialidadRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Veterinario> findById(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Transactional
    @Override
    public Veterinario save(VeterinarioDTO dto) {
        Veterinario vet = new Veterinario();

        vet.setNombre(dto.getNombre());
        vet.setCedula(dto.getCedula());
        vet.setTelefono(dto.getTelefono());
        vet.setCorreo(dto.getCorreo());
        vet.setSueldo(dto.getSueldo());

        List<Especialidad> especialidades = especialidadRepository.findAllById(dto.getEspecialidades());
        vet.setEspecialidades(especialidades);

        return veterinarioRepository.save(vet);
    }

    @Transactional
    @Override
    public Optional<Veterinario> update(Long id, VeterinarioDTO veterinarioDTO) {
        Optional<Veterinario> veterinarioOptional = findById(id);
        if (veterinarioOptional.isPresent()) {
            Veterinario veterinarioUpdate = veterinarioOptional.get();

            veterinarioUpdate.setNombre(veterinarioDTO.getNombre());
            veterinarioUpdate.setCedula(veterinarioDTO.getCedula());
            veterinarioUpdate.setTelefono(veterinarioDTO.getTelefono());
            veterinarioUpdate.setCorreo(veterinarioDTO.getCorreo());
            veterinarioUpdate.setSueldo(veterinarioDTO.getSueldo());
            veterinarioUpdate.setEspecialidades(especialidadRepository.findAllById(veterinarioDTO.getEspecialidades()));

            return Optional.of(veterinarioRepository.save(veterinarioUpdate));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Veterinario> deleteById(Long id) {
        Optional<Veterinario> veterinarioOptional = findById(id);
        if (veterinarioOptional.isPresent()) {
            veterinarioRepository.deleteById(id);
            return veterinarioOptional;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Especialidad saveEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Especialidad> findAllEspecialidades() {
        return especialidadRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Veterinario> addEspecialidad(Long id, Long idEspecialidad) {
        Optional<Veterinario> veterinarioOptional = findById(id);

        if (veterinarioOptional.isPresent()) {
            Veterinario veterinario = veterinarioOptional.get();
            Optional<Especialidad> especialidadOptional = especialidadRepository.findById(idEspecialidad);
            if (especialidadOptional.isPresent()) {
                Especialidad especialidad = especialidadOptional.get();
                veterinario.getEspecialidades().add(especialidad);
                Veterinario veterinarioUpdate = veterinarioRepository.save(veterinario);
                return Optional.of(veterinarioUpdate);
            }
            return Optional.empty();
        }
        return  Optional.empty();
    }
}
