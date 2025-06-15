import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Factura } from '../../models/factura/factura.model';
import { FacturaDTO } from '../../models/dto-factura/factura.dto';
import { FacturaDetalleDTO } from '../../models/dto-factura/factura-detalle.dto';
import { environment } from '../../../environments/environment.prod';
@Injectable({ providedIn: 'root' })
export class FacturaService {
  private baseUrl = `${environment.apiBaseUrl}/facturas`;

  constructor(private http: HttpClient) {}

  getFacturas(): Observable<Factura[]> {
    return this.http.get<Factura[]>(this.baseUrl);
  }

  getFactura(id: number): Observable<Factura> {
    return this.http.get<Factura>(`${this.baseUrl}/${id}`);
  }

  guardarFactura(facturaDTO: FacturaDTO): Observable<Factura> {
    return this.http.post<Factura>(this.baseUrl, facturaDTO);
  }

  actualizarFactura(id: number, facturaDTO: FacturaDTO): Observable<Factura> {
    return this.http.put<Factura>(`${this.baseUrl}/${id}`, facturaDTO);
  }

  eliminarFactura(id: number): Observable<Factura> {
    return this.http.delete<Factura>(`${this.baseUrl}/${id}`);
  }

  agregarDetalle(facturaId: number, detalle: any): Observable<Factura> {
    return this.http.put<Factura>(`${this.baseUrl}/add-detalle/${facturaId}`, detalle);
  }

  eliminarDetalle(facturaId: number, detalleId: number): Observable<Factura> {
    return this.http.put<Factura>(`${this.baseUrl}/remove-detalle/${facturaId}/${detalleId}`, {});
  }

  obtenerFacturaFinal(id: number): Observable<Factura> {
    return this.http.get<Factura>(`${this.baseUrl}/final/${id}`);
  }
}
