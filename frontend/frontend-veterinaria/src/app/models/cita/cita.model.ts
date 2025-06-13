import { Mascota } from '../mascota/mascota.model';
import { EstadoCita } from '../estado-cita/estado-cita.model';
import { Veterinario } from '../veterinario/veterinario.model';

export interface Cita {
  id?: number; // Se marca como opcional para creación
  fecha: string; // ISO format, manejar como string para LocalDate
  sintomas: string;
  precio: number;
  mascota: Mascota;
  estado: EstadoCita;
  veterinarios: Veterinario[];
}
