import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Especie } from '../../models/especie/especie.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EspecieService {
private baseUrl = '/api/especies-razas';

  constructor(private http: HttpClient) {}

getEspecies(): Observable<Especie[]> {
  return this.http.get<Especie[]>(`${this.baseUrl}/especies`);
}
}