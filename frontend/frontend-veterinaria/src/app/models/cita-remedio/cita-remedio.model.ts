import { Cita } from '../cita/cita.model';
import { Remedio } from '../remedio/remedio.model';

export interface CitaRemedio {
  id: number;
  cita: { id: number }; // ya viene como objeto
  remedio: {
    id: number;
    nombre: string;
    precio: number;
  };
  dosis: number;
}
