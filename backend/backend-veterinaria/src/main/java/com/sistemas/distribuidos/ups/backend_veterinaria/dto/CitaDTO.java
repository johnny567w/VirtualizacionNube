package com.sistemas.distribuidos.ups.backend_veterinaria.dto;

import java.time.LocalDate;
import java.util.List;

public class CitaDTO {

    private Long id;
    private LocalDate fecha;
    private String sintomas;
    private Long precio;
    private Long mascotaId;
    private List<Long> veterinarios;
    private Long estadoCitaId;

    public CitaDTO() {
    }

    public CitaDTO(Long id, LocalDate fecha, String sintomas, Long precio, Long mascotaId, List<Long> veterinarios, Long estadoCitaId) {
        this.id = id;
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.precio = precio;
        this.mascotaId = mascotaId;
        this.veterinarios = veterinarios;
        this.estadoCitaId = estadoCitaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public List<Long> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(List<Long> veterinarios) {
        this.veterinarios = veterinarios;
    }

    public Long getEstadoCitaId() {
        return estadoCitaId;
    }

    public void setEstadoCitaId(Long estadoCitaId) {
        this.estadoCitaId = estadoCitaId;
    }
}
