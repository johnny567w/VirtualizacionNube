import { FacturaDetalleDTO } from "./factura-detalle.dto";

export interface FacturaDTO {
  clienteId: number;
  fecha: string;
  total: number;
  detalles: FacturaDetalleDTO[];
}
