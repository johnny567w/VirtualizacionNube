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

@Component({
  selector: 'app-facturacion',
  imports: [FormsModule,CommonModule],
  templateUrl: './facturacion.component.html',
  styleUrl: './facturacion.component.css'
})
export class FacturaComponent  {
}