import { Component } from '@angular/core';
import { Cita } from '../../../models/cita/cita.model';
import { Mascota } from '../../../models/mascota/mascota.model';
import { EstadoCita } from '../../../models/estado-cita/estado-cita.model';
import { Veterinario } from '../../../models/veterinario/veterinario.model';
import { CitaDTO } from '../../../models/dto-cita/cita.dto';
import { MascotaService } from '../../../Services/Mascotas/mascota.service';
import { VeterinarioService } from '../../../Services/Veterinario/veterinario.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Remedio } from '../../../models/remedio/remedio.model';
import { CitaRemedioDTO } from '../../../models/dto-cita/cita-remedio.dto';
import { RemedioService } from '../../../Services/Remedio/remedio.service';
import { RouterModule } from '@angular/router';
import { CitaService } from '../../../Services/Cita/cita.service';
import { EstadoCitaService } from '../../../Services/EstadoCita/estadocita.service';

@Component({
  selector: 'app-cita',
  imports: [FormsModule,CommonModule,RouterModule],
  templateUrl: './cita.component.html',
  styleUrl: './cita.component.css'
})
export class CitaComponent {
citas: Cita[] = [];
  mascotas: Mascota[] = [];
  estados: EstadoCita[] = [];
  veterinarios: Veterinario[] = [];
  estadosCita: EstadoCita[] = [];
   remedios: Remedio[] = [];
  citaRemedios: CitaRemedioDTO[] = [];
  remediosSeleccionados: CitaRemedioDTO[] = [];
esCliente: boolean | null = null;

  nuevaCita: CitaDTO = {
    fecha: '',
    sintomas: '',
    precio: 0,
    mascotaId: 0,
    estadoId: 0,
    veterinariosIds: []
  };

  constructor(
    private citaService: CitaService,
    private mascotaService: MascotaService,
    private estadoService: EstadoCitaService,
  private remedioService: RemedioService,

    private veterinarioService: VeterinarioService
  ) {}

  ngOnInit(): void {
  this.cargarDatos();

this.estadoService.listarEstados2().subscribe({
  next: (data) => {
    this.estados = data;
  },
  error: (err) => {
    console.error('Error al cargar estados de cita', err);
  }
});

  this.remedioService.listarRemedios().subscribe({
    next: (data) => {
      this.remedios = data;
    },
    error: (err) => {
      console.error('Error al cargar remedios', err);
    }
  });
}

  cargarDatos() {
    this.citaService.getCitas().subscribe(data => this.citas = data);
    this.mascotaService.getMascotas().subscribe(data => this.mascotas = data);
    this.veterinarioService.getVeterinarios().subscribe(data => this.veterinarios = data);
  }

  guardarCita() {
    this.citaService.saveCita(this.nuevaCita).subscribe(() => {
      this.cargarDatos();
      this.limpiarFormulario();
    });
  }

  eliminar(id: number) {
    this.citaService.deleteCita(id).subscribe(() => this.cargarDatos());
  }


  limpiarFormulario() {
    this.nuevaCita = {
      fecha: '',
      sintomas: '',
      precio: 0,
      mascotaId: 0,
      estadoId: 0,
      veterinariosIds: []
    };
  }
  nuevoRemedio: CitaRemedioDTO = {
    citaId: 0, 
    remedioId: 0,
    dosis: 1
  };
    agregarRemedio() {
    this.remediosSeleccionados.push({ ...this.nuevoRemedio });
    this.nuevoRemedio = {
      citaId: 0,
      remedioId: 0,
      dosis: 1
    };
  }
  eliminarRemedio(index: number) {
  this.remediosSeleccionados.splice(index, 1);
}

}