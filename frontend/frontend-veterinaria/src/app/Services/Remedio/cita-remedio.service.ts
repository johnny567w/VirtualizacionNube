import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Remedio } from '../../models/remedio/remedio.model';
import { CitaRemedio } from '../../models/cita-remedio/cita-remedio.model';



@Injectable({
  providedIn: 'root'
})
export class CitaRemedioService {

  private apiUrl = '/api/citas-remedios';

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<CitaRemedio[]> {
    return this.http.get<CitaRemedio[]>(this.apiUrl);
  }

  guardar(citaRemedio: CitaRemedio): Observable<CitaRemedio> {
    return this.http.post<CitaRemedio>(this.apiUrl, citaRemedio);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  obtenerPorId(id: number): Observable<CitaRemedio> {
    return this.http.get<CitaRemedio>(`${this.apiUrl}/${id}`);
  }
}