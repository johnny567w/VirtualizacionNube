package com.sistemas.distribuidos.ups.backend_veterinaria.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FacturaDTO {

    private Long id;
    private Long clienteId;
    private LocalDate fecha;
    private Long total;
    private List<Long> facturaDetalles;

    public FacturaDTO() {
    }

    public FacturaDTO(Long id, Long clienteId, LocalDate fecha, Long total, List<Long> facturaDetalles) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
        this.facturaDetalles = facturaDetalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Long> getFacturaDetalles() {
        return facturaDetalles;
    }

    public void setFacturaDetalles(List<Long> facturaDetalles) {
        this.facturaDetalles = facturaDetalles;
    }
}
