package com.sistemas.distribuidos.ups.backend_veterinaria.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cita_remedio")
public class CitaRemedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "remedio_id")
    private Remedio remedio;

    private Integer cantidad;

    public CitaRemedio() {
    }

    public CitaRemedio(Long id, Cita cita, Remedio remedio, Integer cantidad) {
        this.id = id;
        this.cita = cita;
        this.remedio = remedio;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
    public Remedio getRemedio() {
        return remedio;
    }
    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }


}

