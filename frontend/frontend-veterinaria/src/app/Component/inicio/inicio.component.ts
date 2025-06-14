import { Component } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-inicio',
  imports: [FooterComponent,RouterModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {

}
