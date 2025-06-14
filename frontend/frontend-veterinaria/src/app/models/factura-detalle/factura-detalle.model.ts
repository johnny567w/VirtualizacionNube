
import { Cita } from "../cita/cita.model";
import { Remedio } from "../remedio/remedio.model";



export interface FacturaDetalle {
  id?: number;               // Opcional si es nuevo
  cita?: Cita;               // Puede ser nulo (opcional)
  remedio?: Remedio;         // Puede ser nulo (opcional)
  cantidad: number;
  subtotal: number;
}
