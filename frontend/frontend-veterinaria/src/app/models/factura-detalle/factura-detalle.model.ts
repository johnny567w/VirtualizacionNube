
import { Cita } from "../cita/cita.model";
import { Remedio } from "../remedio/remedio.model";



export interface FacturaDetalle {
  id?: number;              
  cita?: Cita;              
  remedio?: Remedio;        
  cantidad: number;
  subtotal: number;
}
