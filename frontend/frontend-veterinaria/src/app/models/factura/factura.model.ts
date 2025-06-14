// src/app/models/factura.model.ts

import { Cliente } from "../cliente/cliente.model";
import { FacturaDetalle } from "../factura-detalle/factura-detalle.model";



export interface Factura {
  id?: number;                       // Es opcional porque puede no existir al crear
  cliente: Cliente;
  fecha: string;                    // Se usa string para fechas ISO (ej. "2025-06-13")
  total: number;
  facturaDetalles: FacturaDetalle[];
}
