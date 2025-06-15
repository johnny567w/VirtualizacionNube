import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Especie } from '../../models/especie/especie.model';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class EspecieService {
  private baseUrl = `${environment.apiBaseUrl}/especies-razas`;

  constructor(private http: HttpClient) {}

getEspecies(): Observable<Especie[]> {
  return this.http.get<Especie[]>(`${this.baseUrl}/especies`);
}
}
