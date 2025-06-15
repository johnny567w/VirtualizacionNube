import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EstadoCita } from '../../models/estado-cita/estado-cita.model';
import { environment } from '../../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class EstadoCitaService {


  private apiUrl = `${environment.apiBaseUrl}/estados-citas`;

  constructor(private http: HttpClient) {}

  getEstados(): Observable<EstadoCita[]> {
    return this.http.get<EstadoCita[]>(this.apiUrl);
  }

  getEstado(id: number): Observable<EstadoCita> {
    return this.http.get<EstadoCita>(`${this.apiUrl}/${id}`);
  }

  saveEstado(estado: EstadoCita): Observable<EstadoCita> {
    return this.http.post<EstadoCita>(this.apiUrl, estado);
  }

  updateEstado(id: number, estado: EstadoCita): Observable<EstadoCita> {
    return this.http.put<EstadoCita>(`${this.apiUrl}/${id}`, estado);
  }

  deleteEstado(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

listarEstados(): Observable<EstadoCita[]> {
  return this.http.get<EstadoCita[]>('/api/estado-cita');
}

}
