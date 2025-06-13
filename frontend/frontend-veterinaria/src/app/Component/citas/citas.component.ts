import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ClienteService } from '../../Services/Cliente/cliente.service';
import { MascotaService } from '../../Services/Mascotas/mascota.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-citas',
  imports: [CommonModule,FormsModule],
  templateUrl: './citas.component.html',
  styleUrl: './citas.component.css'
})
export class CitasComponent {
 esCliente: boolean | null = null;
  clienteSeleccionado: any = null;
  mascotaSeleccionada: any = null;

  clientes: any[] = [];
  mascotas: any[] = [];

  constructor(
    private clienteService: ClienteService,
    private mascotaService: MascotaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.clienteService.getClientes().subscribe(clientes => {
      this.clientes = clientes;
    });

    this.mascotaService.getMascotas().subscribe(mascotas => {
      this.mascotas = mascotas;
    });
  }

  seleccionarCliente(id: number) {
    this.clienteSeleccionado = this.clientes.find(c => c.id == id);
  }

  mascotasDelCliente(): any[] {
    if (!this.clienteSeleccionado) return [];
    return this.mascotas.filter(m => m.cliente_id == this.clienteSeleccionado.id);
  }

  redirigirFormularioClientes() {
    this.router.navigate(['/clientes']);
  }

  registrarCita(form: NgForm) {
    if (form.valid) {
      console.log('Cita registrada:', form.value);
      form.resetForm();
    }
  }
}