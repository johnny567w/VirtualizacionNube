package com.sistemas.distribuidos.ups.backend_veterinaria.services.imp;

import com.sistemas.distribuidos.ups.backend_veterinaria.dto.FacturaDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.dto.FacturaDetalleDTO;
import com.sistemas.distribuidos.ups.backend_veterinaria.models.*;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.*;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.FacturaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FacturaServiceImp implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaDetalleRepository facturaDetalleRepository;
    private final ClienteRepository clienteRepository;
    private final CitaRepository citaRepository;
    private final RemedioRepository remedioRepository;

    public FacturaServiceImp(FacturaRepository facturaRepository, FacturaDetalleRepository facturaDetalleRepository, ClienteRepository clienteRepository, CitaRepository citaRepository, RemedioRepository remedioRepository) {
        this.facturaRepository = facturaRepository;
        this.facturaDetalleRepository = facturaDetalleRepository;
        this.clienteRepository = clienteRepository;
        this.citaRepository = citaRepository;
        this.remedioRepository = remedioRepository;
    }

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Factura save(FacturaDTO facturaDTO) {

        Factura factura = new Factura();

        if (facturaDTO.getId() != null) {
            factura.setId(facturaDTO.getId());
        }

        factura.setFecha(facturaDTO.getFecha());
        factura.setTotal(facturaDTO.getTotal());

        Optional<Cliente>  clienteOptional = clienteRepository.findById(facturaDTO.getClienteId());
        clienteOptional.ifPresent(factura::setCliente);

        List<FacturaDetalle> facturaDetalles = facturaDetalleRepository.findAllById(facturaDTO.getFacturaDetalles());
        factura.setDetalleFacturas(facturaDetalles);

        return facturaRepository.save(factura);
    }

    @Override
    public Optional<Factura> update(Long id, FacturaDTO facturaDTO) {
        Optional<Factura> facturaOptional = findById(id);
        if (facturaOptional.isPresent()){
            facturaDTO.setId(id);
            return Optional.of(save(facturaDTO));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Factura> deleteById(Long id) {
        Optional<Factura> facturaOptional = findById(id);
        if (facturaOptional.isPresent()){
            facturaRepository.deleteById(id);
            return facturaOptional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Factura> findFacturaFinal(Long id) {
        Optional<Factura> facturaOptional = findById(id);
        if (facturaOptional.isPresent()){
            Factura factura = facturaOptional.get();
            factura.calcularTotal();
            return facturaOptional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Factura> addFacturaDetalle(Long idFactura, FacturaDetalleDTO dto) {
        Optional<Factura> facturaOptional = findById(idFactura);

        if (facturaOptional.isPresent()) {
            System.out.println("el factura existe");
            Factura factura = facturaOptional.get();

            FacturaDetalle facturaDetalle = new FacturaDetalle();

            facturaDetalle.setCantidad(dto.getCantidad());
            facturaDetalle.setSubtotal(dto.getSubtotal());

            if (Objects.equals(dto.getTipo(), "C")) {
                System.out.println("es cita");
                Optional<Cita> citaOptional = citaRepository.findById(dto.getCitaId());
                if (citaOptional.isPresent()) {
                    System.out.println("la cita existe");
                    facturaDetalle.setCita(citaOptional.get());
                } else {
                    throw new IllegalArgumentException("La cita con id: " + dto.getId() + " no existe.");
                }
            } else {
                System.out.println("es remedio");
                Optional<Remedio> remedioOptional = remedioRepository.findById(dto.getRemedioId());
                if (remedioOptional.isPresent()) {
                    System.out.println("el remedio existe");
                    facturaDetalle.setRemedio(remedioOptional.get());
                } else {
                    throw new IllegalArgumentException("El remedio con id: " + dto.getId() + " no existe.");
                }
            }

            facturaDetalle.setFactura(factura);
            FacturaDetalle facturaDetalleDb = facturaDetalleRepository.save(facturaDetalle);

            factura.getDetalleFacturas().add(facturaDetalleDb);
            Factura facturaDb = facturaRepository.save(factura);
            return Optional.of(facturaDb);
        }

        return Optional.empty();
    }


    @Override
    public Optional<Factura> removeFacturaDetalle(Long id, Long idFacturaDetalle) {
        Optional<Factura> facturaOptional = findById(id);
        if (facturaOptional.isPresent()){
            Optional<FacturaDetalle> facturaDetalleOptional = facturaDetalleRepository.findById(idFacturaDetalle);

            if (facturaDetalleOptional.isPresent()){
                FacturaDetalle facturaDetalle = facturaDetalleOptional.get();

                facturaDetalleRepository.delete(facturaDetalle);

                Factura factura = facturaOptional.get();
                factura.getDetalleFacturas().removeIf(fd -> fd.getId().equals(idFacturaDetalle));

                return Optional.of(factura);
            }
        }
        return Optional.empty();
    }


    @Override
    public List<FacturaDetalle> findAllFacturaDetalle() {
        return facturaDetalleRepository.findAll();
    }

    @Override
    public Optional<FacturaDetalle> findByIdFacturaDetalle(Long id) {
        return facturaDetalleRepository.findById(id);
    }

}
