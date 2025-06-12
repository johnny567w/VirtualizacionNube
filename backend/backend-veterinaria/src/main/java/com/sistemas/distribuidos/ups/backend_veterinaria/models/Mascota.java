package com.sistemas.distribuidos.ups.backend_veterinaria.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "raza_id")
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Mascota() {
    }

    public Mascota(Long id, String nombre, LocalDate fechaNacimienro, Raza raza, Cliente cliente) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimienro;
        this.raza = raza;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimienro) {
        this.fechaNacimiento = fechaNacimienro;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
