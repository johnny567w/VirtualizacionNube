import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,FormsModule,NgForm,ReactiveFormsModule,Validators } from '@angular/forms';
import { VeterinarioService } from '../../Services/Veterinario/veterinario.service';
import { CommonModule } from '@angular/common';
import { Veterinario } from '../../models/veterinario/veterinario.model';
import { Especialidad } from '../../models/especialidad/especialidad.model';
import { Mensaje } from '../../models/mensaje/mensaje.model';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-veterinario',
  imports: [ReactiveFormsModule,ReactiveFormsModule,CommonModule,FormsModule],
  templateUrl: './veterinario.component.html',
  styleUrl: './veterinario.component.css'
})


export class VeterinarioComponent implements OnInit {
  veterinarios: Veterinario[] = [];
  especialidades: Especialidad[] = [];
  nuevoVet: Veterinario = this.nuevo();
  mensaje = '';
  editandoId: number | null = null;

  constructor(private vetService: VeterinarioService) {}

  ngOnInit(): void {
    this.cargarVeterinarios();
    this.cargarEspecialidades();
  }

  nuevo(): Veterinario {
    return {
      nombre: '',
      cedula: '',
      telefono: '',
      correo: '',
      sueldo: 0,
      especialidades: []
    };
  }

  cargarVeterinarios(): void {
    this.vetService.getVeterinarios().subscribe(data => this.veterinarios = data);
  }

  cargarEspecialidades(): void {
    this.vetService.getEspecialidades().subscribe(data => this.especialidades = data);
  }

guardarVeterinario() {
  if (!this.nuevoVet || !this.nuevoVet.nombre || !this.nuevoVet.cedula) return;

  this.vetService.saveVeterinario(this.nuevoVet).subscribe({
    next: (res: Veterinario) => {
      this.mensaje = 'Veterinario agregado exitosamente.';
      this.nuevoVet = this.nuevo(); // limpia el formulario
      this.cargarVeterinarios();    // recarga la lista
      this.editandoId = null;
      setTimeout(() => this.mensaje = '', 4000);
    },
    error: (err) => {
      this.mensaje = err.error?.mensaje || 'Error al agregar veterinario.';
    }
  });
}

actualizarVeterinario() {
  if (!this.editandoId) return;

  this.vetService.updateVeterinario(this.editandoId, this.nuevoVet).subscribe({
    next: (res: Veterinario) => {
      this.mensaje = 'Veterinario actualizado correctamente.';
      this.nuevoVet = this.nuevo();
      this.cargarVeterinarios();
      this.editandoId = null;
      setTimeout(() => this.mensaje = '', 4000);
    },
    error: (err) => {
      this.mensaje = err.error?.mensaje || 'Error al actualizar veterinario.';
    }
  });
}


eliminarVeterinario(id: number): void {
  this.vetService.deleteVeterinario(id).subscribe({
    next: (res: Mensaje) => {
      this.mensaje = res.mensaje;
      this.cargarVeterinarios();
      console.log('✅', res.mensaje);
      setTimeout(() => this.mensaje = '', 3000);
    },
    error: (err) => {
      console.error('❌ Error al eliminar veterinario:', err.error?.mensaje || err.message);
      this.mensaje = err.error?.mensaje || 'Error desconocido.';
      setTimeout(() => this.mensaje = '', 3000);
    }
  });
}

  editarVeterinario(vet: Veterinario): void {
    this.nuevoVet = { ...vet, especialidades: [...vet.especialidades] };
    this.editandoId = vet.id!;
  }

  cancelarEdicion(): void {
    this.nuevoVet = this.nuevo();
    this.editandoId = null;
  }

  toggleEspecialidad(esp: Especialidad, event: Event): void {
    const checked = (event.target as HTMLInputElement).checked;
    if (checked) {
      this.nuevoVet.especialidades.push(esp);
    } else {
      this.nuevoVet.especialidades = this.nuevoVet.especialidades.filter(e => e.id !== esp.id);
    }
  }

  tieneEspecialidad(vet: Veterinario, esp: Especialidad): boolean {
    return vet.especialidades?.some(e => e.id === esp.id) || false;
  }

  getNombresEspecialidades(vet: Veterinario): string {
    return vet.especialidades?.map(e => e.nombre).join(', ') || '';
  }
}
