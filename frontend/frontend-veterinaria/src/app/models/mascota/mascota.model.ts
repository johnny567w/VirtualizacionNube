import { Cliente } from '../cliente/cliente.model';
import { Raza } from '../raza/raza.model';

export interface Mascota {
  id?: number;
  nombre: string;
  fechaNacimiento: string; // formato ISO como 'YYYY-MM-DD'
  raza: Raza;
  cliente: Cliente;
}
