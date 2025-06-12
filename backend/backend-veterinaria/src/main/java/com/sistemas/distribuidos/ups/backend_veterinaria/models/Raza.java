package com.sistemas.distribuidos.ups.backend_veterinaria.models;

import jakarta.persistence.*;

@Entity
@Table(name = "raza")
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Especie especie;

    public Raza() {
    }

    public Raza(Long id, String nombre, Especie especie) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
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
    public Especie getEspecie() {
        return especie;
    }
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

}
