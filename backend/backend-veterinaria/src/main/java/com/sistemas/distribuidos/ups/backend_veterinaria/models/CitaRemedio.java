package com.sistemas.distribuidos.ups.backend_veterinaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "cita_remedio")
public class CitaRemedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cita_id")
    @JsonIgnore
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "remedio_id")
    private Remedio remedio;

    private Integer dosis;

    public CitaRemedio() {
    }

    public CitaRemedio(Long id, Cita cita, Remedio remedio, Integer dosis) {
        this.id = id;
        this.cita = cita;
        this.remedio = remedio;
        this.dosis = dosis;
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

    public Integer getDosis() {
        return dosis;
    }
    public void setDosis(Integer cantidad) {
        this.dosis = cantidad;
    }


}

