import { Component } from '@angular/core';
import { MomentoFormComponent } from '../momento-form-component/momento-form-component.component';
import { MomentoListComponent } from '../momento-list-component/momento-list-component.component';

@Component({
  selector: 'app-momento-component',
  imports: [MomentoFormComponent, MomentoListComponent],
  templateUrl: './momento-component.component.html',
  styleUrl: './momento-component.component.css'
})
export class MomentoComponentComponent {
}
