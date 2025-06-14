export interface CitaDTO {
  fecha: string;             // Formato ISO, ejemplo: '2025-06-14'
  sintomas: string;
  precio: number;
  mascotaId: number;
  estadoId: number;
  veterinariosIds: number[]; // Lista de IDs de veterinarios
}
