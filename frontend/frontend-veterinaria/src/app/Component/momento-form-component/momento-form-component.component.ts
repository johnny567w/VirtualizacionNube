import { Component } from '@angular/core';
import { MomentoService } from '../../Services/Momento/momento.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-momento-form-component',
  imports: [FormsModule],
  templateUrl: './momento-form-component.component.html',
  styleUrl: './momento-form-component.component.css'
})
export class MomentoFormComponent {
  descripcion = '';
  archivo: File | null = null;

  constructor(private servicio: MomentoService) {}

  onArchivoSeleccionado(event: any) {
    this.archivo = event.target.files[0];
  }

  subir() {
    if (this.archivo) {
      this.servicio.subir(this.descripcion, this.archivo).subscribe({
        next: () => alert('Momento subido'),
        error: () => alert('Error al subir')
      });
    }
  }
}

