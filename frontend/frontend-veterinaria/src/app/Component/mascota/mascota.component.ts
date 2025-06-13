import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ClienteService } from '../../Services/Cliente/cliente.service';
import { MascotaService } from '../../Services/Mascotas/mascota.service';
import { RazaService } from '../../Services/Raza/raza.service';
import { EspecieService } from '../../Services/Especie/especie.service';
import { Raza } from '../../models/raza/raza.model';
import { Cliente } from '../../models/cliente/cliente.model';
import { Mensaje } from '../../models/mensaje/mensaje.model';
import { Mascota } from '../../models/mascota/mascota.model';
import { Especie } from '../../models/especie/especie.model';

@Component({
  selector: 'app-mascota',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './mascota.component.html',
  styleUrls: ['./mascota.component.css']
})
export class MascotaComponent implements OnInit {

  mascotas: Mascota[] = [];
  clientes: Cliente[] = [];
  especies: Especie[] = [];

  nuevaMascota: Mascota = {
    nombre: '',
    fechaNacimiento: '',
    cliente: {} as Cliente,
    raza: {
      id: 0,
      nombre: '',
      especie: {} as Especie
    }
  };

  constructor(
    private mascotaService: MascotaService,
    private clienteService: ClienteService,
    private especieService: EspecieService,
    private razaService: RazaService
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos() {
    this.mascotaService.getMascotas().subscribe(data => this.mascotas = data);
    this.clienteService.getClientes().subscribe(data => this.clientes = data);
    this.especieService.getEspecies().subscribe(data => this.especies = data);
  }

guardarMascota() {
  const especieSeleccionada = this.nuevaMascota.raza.especie;

  if (!especieSeleccionada || !especieSeleccionada.id) {
    console.error("Debe seleccionar una especie válida.");
    return;
  }

  const nuevaRaza: Raza = {
  nombre: this.nuevaMascota.raza.nombre,
  especie: {
    id: especieSeleccionada.id,
    nombre: especieSeleccionada.nombre
  }
};


  this.razaService.saveRaza(nuevaRaza as Raza).subscribe({
    next: razaGuardada => {
      this.nuevaMascota.raza = razaGuardada;

      this.mascotaService.saveMascota(this.nuevaMascota).subscribe(() => {
        this.cargarDatos(); // Refrescar lista
        this.limpiarFormulario();
      });
    },
    error: error => {
      console.error("Error al guardar la raza:", error);
    }
  });
}

limpiarFormulario() {
  this.nuevaMascota = {
    nombre: '',
    fechaNacimiento: '',
    cliente: {} as Cliente,
    raza: {
      nombre: '',
      especie: {
        id: 0,
        nombre: ''
      }
    }
  };
}



  eliminar(id: number) {
    this.mascotaService.deleteMascota(id).subscribe(() => this.cargarDatos());
  }
}
