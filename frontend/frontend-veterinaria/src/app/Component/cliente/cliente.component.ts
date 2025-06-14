import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, NgForm, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ClienteService } from '../../Services/Cliente/cliente.service';
import { MascotaService } from '../../Services/Mascotas/mascota.service';
import { Cliente } from '../../models/cliente/cliente.model';

@Component({
  selector: 'app-cliente',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, HttpClientModule],
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {
 clientes: Cliente[] = [];
  nuevoCliente: Cliente = this.resetCliente();
  editandoId: number | null = null;
  mensajeBack: string = '';

  constructor(private clienteService: ClienteService) {}

  ngOnInit(): void {
    this.cargarClientes();
  }

  cargarClientes(): void {
    this.clienteService.getClientes().subscribe((data) => {
      this.clientes = data;
    });
  }

  guardarCliente(): void {
    this.clienteService.saveCliente(this.nuevoCliente).subscribe((res) => {
      this.mensajeBack = 'Cliente agregado correctamente.';
      this.cargarClientes();
      this.nuevoCliente = this.resetCliente();
    });
  }

  editarCliente(cliente: Cliente): void {
    this.nuevoCliente = { ...cliente };
    this.editandoId = cliente.id!;
    this.mensajeBack = '';
  }

  actualizarCliente(): void {
    if (!this.editandoId) return;
    this.clienteService.updateCliente(this.editandoId, this.nuevoCliente).subscribe((res) => {
      this.mensajeBack = 'Cliente actualizado correctamente.';
      this.cargarClientes();
      this.nuevoCliente = this.resetCliente();
      this.editandoId = null;
    });
  }

  eliminarCliente(id: number): void {
    this.clienteService.deleteCliente(id).subscribe((res) => {
      this.mensajeBack = 'Cliente eliminado correctamente.';
      this.cargarClientes();
    });
  }

  cancelarEdicion(): void {
    this.nuevoCliente = this.resetCliente();
    this.editandoId = null;
    this.mensajeBack = '';
  }

  resetCliente(): Cliente {
    return {
      nombre: '',
      cedula: '',
      telefono: '',
      correo: ''
    };
  }
}