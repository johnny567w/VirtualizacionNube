// src/app/models/factura.model.ts

import { Cliente } from "../cliente/cliente.model";
import { FacturaDetalle } from "../factura-detalle/factura-detalle.model";



export interface Factura {
  id?: number;                       
  cliente: Cliente;
  fecha: string;                    
  total: number;
  facturaDetalles: FacturaDetalle[];
}
