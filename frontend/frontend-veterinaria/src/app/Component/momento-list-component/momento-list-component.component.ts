import { Component, OnInit } from '@angular/core';
import { Momento } from '../../models/momento/momento.model';
import { MomentoService } from '../../Services/Momento/momento.service';
import { CommonModule } from '@angular/common';
import { environment } from '../../../environments/environment.prod';

@Component({
  selector: 'app-momento-list-component',
  imports: [CommonModule],
  templateUrl: './momento-list-component.component.html',
  styleUrl: './momento-list-component.component.css'
})
export class MomentoListComponent implements OnInit {
  momentos: Momento[] = [];

  constructor(private servicio: MomentoService) {}

  ngOnInit() {
    this.servicio.listar().subscribe(data => this.momentos = data);
  }

  getImagenUrl(url: string): string {
    return `${environment.apiBaseUrl}${url}`;
  }
}
