package com.sistemas.distribuidos.ups.backend_veterinaria.dto;

public class FacturaDetalleDTO {

    private Long id;
    private Long citaId;
    private Long remedioId;
    private Integer cantidad;
    private Long subtotal;
    private String tipo;

    public FacturaDetalleDTO() {
    }

    public FacturaDetalleDTO(Long id, Long citaId, Long remedioId, Integer cantidad, Long subtotal, String tipo) {
        this.id = id;
        this.citaId = citaId;
        this.remedioId = remedioId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Long getRemedioId() {
        return remedioId;
    }

    public void setRemedioId(Long remedioId) {
        this.remedioId = remedioId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
