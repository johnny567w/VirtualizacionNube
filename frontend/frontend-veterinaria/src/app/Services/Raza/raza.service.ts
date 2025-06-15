import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Raza } from '../../models/raza/raza.model';
import { Especie } from '../../models/especie/especie.model';
import { environment } from '../../../environments/environment.prod';

@Injectable({ providedIn: 'root' })
export class RazaService {
  private baseUrl = `${environment.apiBaseUrl}/especies-razas`;

  constructor(private http: HttpClient) {}

  saveRaza(raza: Raza): Observable<Raza> {
    return this.http.post<Raza>(`${this.baseUrl}/razas`, raza);
  }

  getRazasPorEspecie(id: number): Observable<Raza[]> {
    return this.http.get<Raza[]>(`${this.baseUrl}/raza-por-especie/${id}`);
  }

  getAllRazas(): Observable<Raza[]> {
    return this.http.get<Raza[]>(`${this.baseUrl}/razas`);
  }
}
