package com.sistemas.distribuidos.ups.backend_veterinaria.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate fecha;
    private Long total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private List<FacturaDetalle> facturaDetalles;

    public Factura() {
        this.facturaDetalles = new ArrayList<>();
    }

    public Factura(Long id, LocalDate fecha, Long total, List<FacturaDetalle> facturaDetalles) {
        this();
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public List<FacturaDetalle> getDetalleFacturas() {
        return facturaDetalles;
    }

    public void setDetalleFacturas(List<FacturaDetalle> facturaDetalles) {
        this.facturaDetalles = facturaDetalles;
    }

    public void calcularTotal() {
        for (FacturaDetalle detalle : this.facturaDetalles) {
            if (null == detalle.getCita()){
                detalle.setSubtotal( detalle.getRemedio().getPrecio() * detalle.getCantidad() );
            } else{
                detalle.setSubtotal( detalle.getCita().getPrecio());
            }
            this.total += detalle.getSubtotal();
        }
    }
}
