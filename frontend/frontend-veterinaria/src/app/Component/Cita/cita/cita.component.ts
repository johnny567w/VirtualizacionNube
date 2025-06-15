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
import { Cliente } from '../../../models/cliente/cliente.model';
import { ClienteService } from '../../../Services/Cliente/cliente.service';

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
clientes: Cliente[] = [];
clienteSeleccionadoId: number | null = null;
mascotasFiltradas: Mascota[] = [];
busquedaVeterinario: string = '';
filtroCliente: string = '';
clientesFiltrados: Cliente[] = [];
filtroMascota: string = '';

mascotasClienteBase: Mascota[] = [];

nuevaCita: CitaDTO = {
  fecha: '',
  sintomas: '',
  precio: 0,
  mascotaId: 0,
  estadoCitaId: 0, 
  veterinarios: []
};


  constructor(
    private citaService: CitaService,
    private mascotaService: MascotaService,
    private estadoService: EstadoCitaService,
  private remedioService: RemedioService,
private clienteService: ClienteService,
    private veterinarioService: VeterinarioService
  ) {}

  ngOnInit(): void {
  this.cargarDatos();
  this.clienteService.getClientes().subscribe((data: Cliente[]) => {
    this.clientes = data;
  });


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
  this.nuevaCita.mascotaId = +this.nuevaCita.mascotaId;
  this.nuevaCita.estadoCitaId = +this.nuevaCita.estadoCitaId;

  const citaParaEnviar = {
    ...this.nuevaCita,
    veterinarios: this.nuevaCita.veterinarios 
  };

  console.log("📦 Datos de la cita a enviar:", citaParaEnviar);

  if (
    !citaParaEnviar.fecha ||
    !citaParaEnviar.sintomas ||
    !citaParaEnviar.mascotaId ||
    !citaParaEnviar.estadoCitaId ||
    !citaParaEnviar.veterinarios.length
  ) {
    alert("Por favor, completa todos los campos obligatorios");
    return;
  }

  this.citaService.saveCita(citaParaEnviar).subscribe((citaGuardada) => {
    console.log("✅ Cita guardada correctamente:", citaGuardada);

    // Enviar los remedios seleccionados
    this.remediosSeleccionados.forEach(remedio => {
      const detalle = {
        citaId: citaGuardada.id,
        remedioId: remedio.remedioId,
        dosis: remedio.dosis
      };

      this.citaService.agregarRemedio(detalle).subscribe({
        next: () => console.log("🧪 Remedio agregado:", detalle),
        error: (err) => console.error("❌ Error al agregar remedio:", detalle, err)
      });
    });

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
    estadoCitaId: 0,
    veterinarios: []
  };

  this.clienteSeleccionadoId = 0;
  this.filtroCliente = '';
  this.filtroMascota = '';
  this.mascotasFiltradas = [];
  this.mascotasClienteBase = [];
  this.busquedaVeterinario = '';
  this.remediosSeleccionados = [];
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

convertirCliente(id: any) {
  this.clienteSeleccionadoId = +id;
  this.mascotasFiltradas = this.mascotas.filter(m => m.cliente?.id === +id);
}

convertirMascota(id: any) {
  this.nuevaCita.mascotaId = +id;
}

convertirVeterinarios(ids: any[]) {
  this.nuevaCita.veterinarios = ids.map(id => +id);
}
onVeterinarioChange(event: any) {
  const id = +event.target.value;
  if (event.target.checked) {
    if (!this.nuevaCita.veterinarios.includes(id)) {
      this.nuevaCita.veterinarios.push(id);
    }
  } else {
    this.nuevaCita.veterinarios = this.nuevaCita.veterinarios.filter(v => v !== id);
  }
}
veterinariosFiltrados(): Veterinario[] {
  if (!this.busquedaVeterinario) {
    return this.veterinarios;
  }
  const texto = this.busquedaVeterinario.toLowerCase();
  return this.veterinarios.filter(vet => vet.nombre.toLowerCase().includes(texto));
}

toggleVeterinario(id: number | undefined) {
  if (id == null) return;

  const idNum = +id;
  const index = this.nuevaCita.veterinarios.indexOf(idNum);
  if (index > -1) {
    this.nuevaCita.veterinarios.splice(index, 1);
  } else {
    this.nuevaCita.veterinarios.push(idNum);
  }
}


filtrarClientes() {
  const term = this.filtroCliente.toLowerCase();
  this.clientesFiltrados = this.clientes.filter(c =>
    c.nombre.toLowerCase().includes(term)
  );
}


filtrarMascotasPorCliente() {
  this.mascotasClienteBase = this.mascotas.filter(m => {
    return m.cliente && m.cliente.id === this.clienteSeleccionadoId;
  });
  this.mascotasFiltradas = [...this.mascotasClienteBase];

  // 🧠 Limpia la selección anterior de mascota
  this.nuevaCita.mascotaId = 0;

  console.log('Mascotas filtradas por cliente:', this.mascotasFiltradas);
}


filtrarMascotas() {
  const term = this.filtroMascota.toLowerCase();

  this.mascotasFiltradas = this.mascotasClienteBase.filter(m =>
    m.nombre?.toLowerCase().includes(term)
  );

  console.log('Mascotas filtradas por nombre:', this.mascotasFiltradas);
}

}