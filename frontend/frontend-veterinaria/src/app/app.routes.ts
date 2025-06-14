import { Routes } from '@angular/router';
import { InicioComponent } from './Component/inicio/inicio.component';
import { CitasComponent } from './Component/citas/citas.component';
import { FacturacionComponent } from './Component/facturacion/facturacion.component';
import { AboutComponent } from './Component/about/about.component';
import { VeterinarioComponent } from './Component/veterinario/veterinario.component';
import { ClienteComponent } from './Component/cliente/cliente.component';
import { MascotaComponent } from './Component/mascota/mascota.component';

export const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'citas', component: CitasComponent },
  { path: 'veterinario', component: VeterinarioComponent },
  { path: 'facturacion', component: FacturacionComponent },
  { path: 'about', component: AboutComponent },
  { path: 'clientes', component: ClienteComponent },
  { path: 'mascotas', component: MascotaComponent },

  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
];