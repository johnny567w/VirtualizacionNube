export interface FacturaDTO {
  id?: number;
  clienteId: number;
  fecha: string;
  total: number;
  facturaDetalles: number[]; 
}
