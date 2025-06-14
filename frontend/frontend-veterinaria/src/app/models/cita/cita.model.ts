import { Mascota } from '../mascota/mascota.model';
import { EstadoCita } from '../estado-cita/estado-cita.model';
import { Veterinario } from '../veterinario/veterinario.model';

export interface Cita {
  id: number;
  fecha: string; // '2025-06-14'
  sintomas: string;
  precio: number;
  estado: EstadoCita; // 👈 importante
  mascota: any;       // puedes tiparlo mejor si ya tienes modelo
  veterinarios: any[]; // igual, se puede tipar luego
}