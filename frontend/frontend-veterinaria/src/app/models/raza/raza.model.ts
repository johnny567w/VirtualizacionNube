import { Especie } from '../especie/especie.model';

export interface Raza {
  id?: number;               // <-- antes era obligatorio, ahora opcional
  nombre: string;
  especie: Especie;
}