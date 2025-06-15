import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../models/cliente/cliente.model';
import { Remedio } from '../../models/remedio/remedio.model';
import { Cita } from '../../models/cita/cita.model';
import { Factura } from '../../models/factura/factura.model';
import { FacturaDTO } from '../../models/dto-factura/factura.dto';
import { FacturaDetalleDTO } from '../../models/dto-factura/factura-detalle.dto';
import { FacturaService } from '../../Services/Factura/factura.service';
import { ClienteService } from '../../Services/Cliente/cliente.service';
import { CitaService } from '../../Services/Cita/cita.service';
import { RemedioService } from '../../Services/Remedio/remedio.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Mascota } from '../../models/mascota/mascota.model';
import { MascotaService } from '../../Services/Mascotas/mascota.service';
import { FacturaDetalle } from '../../models/factura-detalle/factura-detalle.model';
import { HttpErrorResponse } from '@angular/common/http';
import { CitaRemedio } from '../../models/cita-remedio/cita-remedio.model';
import { CitaRemedioService } from '../../Services/Remedio/cita-remedio.service';

@Component({
  selector: 'app-facturacion',
  imports: [FormsModule,CommonModule],
  templateUrl: './facturacion.component.html',
  styleUrl: './facturacion.component.css'
})
 export class FacturaComponent implements OnInit {

  clientes: Cliente[] = [];
  filtroCliente: string = '';
  clientesFiltrados: Cliente[] = [];
  clienteSeleccionadoId: number = 0;
  clienteSeleccionado?: Cliente;
todosLosRemedios: Remedio[] = [];

  mascotas: Mascota[] = [];
  mascotasClienteBase: Mascota[] = [];
  mascotasFiltradas: Mascota[] = [];
  filtroMascota: string = '';
  mascotaSeleccionadaId: number = 0;
todosCitaRemedios: CitaRemedio[] = [];
facturas: Factura[] = [];

  citasCliente: Cita[] = [];
  citaSeleccionada!: Cita;


  detallesFactura: FacturaDetalle[] = [];

constructor(
  private clienteService: ClienteService,
  private citaService: CitaService,
  private facturaService: FacturaService,
  private mascotaService: MascotaService,
  private remedioService: RemedioService,
  private citaRemedioService: CitaRemedioService 

) {}

ngOnInit(): void {
  this.clienteService.getClientes().subscribe(clientes => {
    this.clientes = clientes;
    this.clientesFiltrados = [...clientes];
  });

  this.mascotaService.getMascotas().subscribe(mascotas => {
    this.mascotas = mascotas;
  });
this.citaRemedioService.listarTodos().subscribe((data: CitaRemedio[]) => {
  this.todosCitaRemedios = data;
  console.log("📦 todosCitaRemedios cargados:", this.todosCitaRemedios);
});
  this.cargarFacturas();

}



  filtrarClientes() {
    const term = this.filtroCliente.toLowerCase();
    this.clientesFiltrados = this.clientes.filter(c =>
      c.nombre.toLowerCase().includes(term)
    );
  }

  seleccionarCliente(id: number) {
    this.clienteSeleccionadoId = id;
    this.clienteSeleccionado = this.clientes.find(c => c.id === id);
    this.filtrarMascotasPorCliente();
  }

filtrarMascotasPorCliente() {
  this.mascotasClienteBase = this.mascotas.filter(m =>
    m.cliente && m.cliente.id === this.clienteSeleccionadoId
  );
  this.mascotasFiltradas = [...this.mascotasClienteBase];
}


  filtrarMascotas() {
    const term = this.filtroMascota.toLowerCase();
    this.mascotasFiltradas = this.mascotasClienteBase.filter(m =>
      m.nombre?.toLowerCase().includes(term)
    );
  }

  buscarCitasDeMascota() {
    if (this.mascotaSeleccionadaId) {
      this.citaService.getByMascota(this.mascotaSeleccionadaId).subscribe(citas => {
        this.citasCliente = citas;
      });
    }
  }

seleccionarCita(cita: Cita) {
  this.citaSeleccionada = cita;

  const filtrados = this.todosCitaRemedios.filter(r => r.cita?.id === cita.id);

  console.log("🎯 CitaRemedios filtrados:", filtrados);

  this.detallesFactura = filtrados.map(r => {
    return {
      remedio: {
        id: r.remedio.id,
        nombre: r.remedio.nombre,
        precio: r.remedio.precio
      },
      cantidad: r.dosis,
      subtotal: r.remedio.precio * r.dosis
    };
  });

  console.log("💊 Detalles de la factura:", this.detallesFactura);
}


  totalFactura(): number {
    const totalRemedios = this.detallesFactura.reduce((sum, d) => sum + d.subtotal, 0);
    return (this.citaSeleccionada?.precio ?? 0) + totalRemedios;
  }

  generarFactura() {
    if (!this.clienteSeleccionado || !this.clienteSeleccionado.id) {
      alert('Debe seleccionar un cliente.');
      return;
    }

    const facturaDTO = {
      clienteId: this.clienteSeleccionado.id,
      fecha: new Date().toISOString().split('T')[0],
      total: this.totalFactura(),
      facturaDetalles: []
    };

    this.facturaService.guardarFactura(facturaDTO).subscribe({
      next: res => alert('Factura guardada correctamente'),
      error: err => console.error('Error al guardar factura', err)
    });
  }
formatearCita(cita: Cita): string {
  const fecha = cita.fecha ?? '';
  const vets = cita.veterinarios?.map(v => v.nombre).join(', ') ?? '';
  return `${fecha} - ${vets}`;
}
cargarFacturas() {
  this.facturaService.getFacturas().subscribe({
    next: data => this.facturas = data,
    error: err => console.error('Error al cargar facturas', err)
  });
}
}
