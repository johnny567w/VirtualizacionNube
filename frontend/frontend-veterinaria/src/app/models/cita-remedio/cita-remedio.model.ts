import { Cita } from '../cita/cita.model';
import { Remedio } from '../remedio/remedio.model';

export interface CitaRemedio {
  id?: number;             // Opcional en creación
  cita: Cita;              // Referencia a cita
  remedio: Remedio;        // Referencia a remedio
  dosis: number;           // Cantidad de unidades o dosis
}
