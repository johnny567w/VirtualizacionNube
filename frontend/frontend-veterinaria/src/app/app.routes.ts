import { Routes } from '@angular/router';
import { InicioComponent } from './Component/inicio/inicio.component';
import { AboutComponent } from './Component/about/about.component';
import { VeterinarioComponent } from './Component/veterinario/veterinario.component';
import { ClienteComponent } from './Component/cliente/cliente.component';
import { MascotaComponent } from './Component/mascota/mascota.component';
import { CitaComponent } from './Component/Cita/cita/cita.component';
import { FacturaComponent } from './Component/facturacion/facturacion.component';

export const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'citas', component: CitaComponent },
  { path: 'veterinario', component: VeterinarioComponent },
  { path: 'facturacion', component: FacturaComponent },
  { path: 'about', component: AboutComponent },
  { path: 'clientes', component: ClienteComponent },
  { path: 'mascotas', component: MascotaComponent },

  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
];