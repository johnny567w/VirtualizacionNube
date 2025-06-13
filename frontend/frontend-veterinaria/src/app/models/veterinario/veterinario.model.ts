import { Especialidad } from '../especialidad/especialidad.model';

export interface Veterinario {
  id?: number;
  nombre: string;
  cedula: string;
  telefono: string;
  correo: string;
  sueldo: number;
  especialidades: Especialidad[];
}
