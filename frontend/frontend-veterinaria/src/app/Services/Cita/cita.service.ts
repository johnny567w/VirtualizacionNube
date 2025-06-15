import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cita } from '../../models/cita/cita.model';
import { CitaDTO } from '../../models/dto-cita/cita.dto';
import { CitaRemedioDTO } from '../../models/dto-cita/cita-remedio.dto';

@Injectable({
  providedIn: 'root'
})
export class CitaService {
  private baseUrl = '/api/citas';

  constructor(private http: HttpClient) {}

  getCitas(): Observable<Cita[]> {
    return this.http.get<Cita[]>(this.baseUrl);
  }

  getCitaById(id: number): Observable<Cita> {
    return this.http.get<Cita>(`${this.baseUrl}/${id}`);
  }

  getByMascota(idMascota: number): Observable<Cita[]> {
  return this.http.get<Cita[]>(`${this.baseUrl}/mascotas/${idMascota}`);
}

  saveCita(citaDTO: CitaDTO): Observable<Cita> {
    return this.http.post<Cita>(this.baseUrl, citaDTO);
  }

  updateCita(id: number, citaDTO: CitaDTO): Observable<Cita> {
    return this.http.put<Cita>(`${this.baseUrl}/${id}`, citaDTO);
  }

  deleteCita(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

cambiarEstado(citaId: number, estadoId: number) {
  return this.http.put(`/api/citas/${citaId}/${estadoId}`, {});
}
agregarRemedio(remedioDTO: any): Observable<any> {
  return this.http.post('/api/citas-remedios', remedioDTO);
}
obtenerRemediosPorCita(citaId: number): Observable<CitaRemedioDTO[]> {
  return this.http.get<CitaRemedioDTO[]>(`/api/citas-remedios/cita/${citaId}`);
}

}

