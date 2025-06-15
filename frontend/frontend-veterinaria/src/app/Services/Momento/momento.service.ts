import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Momento } from '../../models/momento/momento.model';
import { environment } from '../../../environments/environment.prod';

@Injectable({ providedIn: 'root' })
export class MomentoService {

  private apiUrl = `${environment.apiBaseUrl}/momentos`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Momento[]> {
    return this.http.get<Momento[]>(this.apiUrl);
  }

  subir(descripcion: string, archivo: File): Observable<Momento> {
    const formData = new FormData();
    formData.append('descripcion', descripcion);
    formData.append('foto', archivo);
    return this.http.post<Momento>(this.apiUrl, formData);
  }
}
