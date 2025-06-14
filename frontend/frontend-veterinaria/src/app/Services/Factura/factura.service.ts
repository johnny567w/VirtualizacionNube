import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Factura } from '../../models/factura/factura.model';
import { FacturaDTO } from '../../models/dto-factura/factura.dto';
import { FacturaDetalleDTO } from '../../models/dto-factura/factura-detalle.dto';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {
  private apiUrl = '/api/facturas';

  constructor(private http: HttpClient) {}

  getFacturas(): Observable<Factura[]> {
    return this.http.get<Factura[]>(this.apiUrl);
  }

  saveFactura(dto: FacturaDTO): Observable<Factura> {
    return this.http.post<Factura>(this.apiUrl, dto);
  }

  updateFactura(id: number, dto: FacturaDTO): Observable<Factura> {
    return this.http.put<Factura>(`${this.apiUrl}/${id}`, dto);
  }

  deleteFactura(id: number): Observable<Factura> {
    return this.http.delete<Factura>(`${this.apiUrl}/${id}`);
  }

  addDetalle(idFactura: number, dto: FacturaDetalleDTO): Observable<Factura> {
    return this.http.put<Factura>(`${this.apiUrl}/add-detalle/${idFactura}`, dto);
  }

  removeDetalle(idFactura: number, idDetalle: number): Observable<Factura> {
    return this.http.put<Factura>(`${this.apiUrl}/remove-detalle/${idFactura}/${idDetalle}`, {});
  }

  getFacturaFinal(id: number): Observable<Factura> {
    return this.http.get<Factura>(`${this.apiUrl}/final/${id}`);
  }
}