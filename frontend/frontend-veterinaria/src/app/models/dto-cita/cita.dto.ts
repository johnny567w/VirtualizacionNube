export interface CitaDTO {
  fecha: string;
  sintomas: string;
  precio: number;
  mascotaId: number;
  estadoCitaId: number;
  veterinarios: number[]; // 👈 cambiar aquí
}
