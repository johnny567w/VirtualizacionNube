import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Veterinario } from '../../models/veterinario/veterinario.model';
import { Especialidad } from '../../models/especialidad/especialidad.model';
import { Mensaje } from '../../models/mensaje/mensaje.model';

@Injectable({ providedIn: 'root' })
export class VeterinarioService {
  private apiUrl = '/api/veterinarios';

  constructor(private http: HttpClient) {}

  getVeterinarios(): Observable<Veterinario[]> {
    return this.http.get<Veterinario[]>(this.apiUrl);
  }

  saveVeterinario(vet: Veterinario): Observable<Veterinario> {
    const dto = this.convertToDto(vet);
    return this.http.post<Veterinario>(this.apiUrl, dto);
  }

  updateVeterinario(id: number, vet: Veterinario): Observable<Veterinario> {
    const dto = this.convertToDto(vet);
    return this.http.put<Veterinario>(`${this.apiUrl}/${id}`, dto);
  }

deleteVeterinario(id: number): Observable<Mensaje> {
  return this.http.delete<Mensaje>(`${this.apiUrl}/${id}`);
}

  getEspecialidades(): Observable<Especialidad[]> {
    return this.http.get<Especialidad[]>(`${this.apiUrl}/especialidades`);
  }

  addEspecialidad(idVet: number, idEsp: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/especialidades/${idVet}/${idEsp}`, null);
  }

  private convertToDto(vet: Veterinario) {
    return {
      nombre: vet.nombre,
      cedula: vet.cedula,
      telefono: vet.telefono,
      correo: vet.correo,
      sueldo: vet.sueldo,
      especialidades: vet.especialidades.map(e => e.id)
    };
  }
}