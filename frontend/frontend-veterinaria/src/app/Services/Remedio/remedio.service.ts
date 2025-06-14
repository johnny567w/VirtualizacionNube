import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Remedio } from '../../models/remedio/remedio.model';



@Injectable({
  providedIn: 'root'
})
export class RemedioService {
  private apiUrl = '/api/remedios';

  constructor(private http: HttpClient) {}

listarRemedios(): Observable<Remedio[]> {
return this.http.get<Remedio[]>(`${this.apiUrl}`);
}

  obtener(id: number): Observable<Remedio> {
    return this.http.get<Remedio>(`${this.apiUrl}/${id}`);
  }

  guardar(remedio: Remedio): Observable<Remedio> {
    return this.http.post<Remedio>(this.apiUrl, remedio);
  }

  actualizar(id: number, remedio: Remedio): Observable<Remedio> {
    return this.http.put<Remedio>(`${this.apiUrl}/${id}`, remedio);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}