package com.sistemas.distribuidos.ups.backend_veterinaria.dto;

import java.util.List;

public class VeterinarioDTO {

    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private Long sueldo;
    private List<Long> especialidades; // Solo los IDs

    public VeterinarioDTO() {
    }

    public VeterinarioDTO(String nombre, String cedula, String telefono, String correo, Long sueldo, List<Long> especialidades) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.sueldo = sueldo;
        this.especialidades = especialidades;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getSueldo() {
        return sueldo;
    }

    public void setSueldo(Long sueldo) {
        this.sueldo = sueldo;
    }

    public List<Long> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Long> especialidades) {
        this.especialidades = especialidades;
    }
}
