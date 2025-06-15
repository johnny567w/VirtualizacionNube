import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EstadoCita } from '../../models/estado-cita/estado-cita.model';
import { environment } from '../../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})

export class EstadoCitaService {
  private apiUrl = `${environment.apiBaseUrl}/citas/estado-citas`;

  constructor(private http: HttpClient) {}
listarEstados2(): Observable<EstadoCita[]> {
  return this.http.get<EstadoCita[]>(this.apiUrl);
}
}
