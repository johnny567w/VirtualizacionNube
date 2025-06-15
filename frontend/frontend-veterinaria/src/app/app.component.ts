import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { FooterComponent } from './Component/footer/footer.component';
import { InicioComponent } from './Component/inicio/inicio.component';
import { CitaComponent } from './Component/Cita/cita/cita.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend-veterinaria';
}
