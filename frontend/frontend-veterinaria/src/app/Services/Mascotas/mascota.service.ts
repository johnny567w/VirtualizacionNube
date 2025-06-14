import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Mascota } from '../../models/mascota/mascota.model';
import { Raza } from '../../models/raza/raza.model';
import { Especie } from '../../models/especie/especie.model';
import { Cliente } from '../../models/cliente/cliente.model';

@Injectable({ providedIn: 'root' })
export class MascotaService {
  private apiUrl = '/api/mascotas';

  constructor(private http: HttpClient) {}

  getMascotas(): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(this.apiUrl);
  }

  getMascotaById(id: number): Observable<Mascota> {
    return this.http.get<Mascota>(`${this.apiUrl}/${id}`);
  }

  saveMascota(mascota: Mascota): Observable<Mascota> {
    return this.http.post<Mascota>(this.apiUrl, mascota);
  }

  updateMascota(id: number, mascota: Mascota): Observable<Mascota> {
    return this.http.put<Mascota>(`${this.apiUrl}/${id}`, mascota);
  }

  deleteMascota(id: number): Observable<Mascota> {
    return this.http.delete<Mascota>(`${this.apiUrl}/${id}`);
  }

  getRazas(): Observable<Raza[]> {
    return this.http.get<Raza[]>('/api/razas'); // Asegúrate que esta ruta esté expuesta
  }

  getEspecies(): Observable<Especie[]> {
    return this.http.get<Especie[]>('/api/especies'); // Asegúrate que esta ruta esté expuesta
  }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>('/api/clientes');
  }
  getByCliente(clienteId: number): Observable<Mascota[]> {
  return this.http.get<Mascota[]>(`${this.apiUrl}/cliente/${clienteId}`);
}

}