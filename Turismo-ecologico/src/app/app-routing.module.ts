import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { ClimbInformationComponent } from './components/climb-information/climb-information.component';
import { CountryInfoComponent } from './components/country-info/country-info.component';
import { GaleryClientComponent } from './components/galery-client/galery-client.component';
import { GalerySupplierComponent } from './components/galery-supplier/galery-supplier.component';
import { LoginComponent } from './components/login/login.component';
import { MapsComponent } from './components/maps/maps.component';
import { RegisterComponent } from './components/register/register.component';
import { ServiceInfoComponent } from './components/service-info/service-info.component';
import { ServiceInfoSupplierComponent } from './components/service-info-supplier/service-info-supplier.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Redirección por defecto
  { path: 'cart', component: CartComponent },
  { path: 'climb-information', component: ClimbInformationComponent },
  { path: 'country-info', component: CountryInfoComponent },
  { path: 'galery-client', component: GaleryClientComponent },
  { path: 'galery-supplier', component: GalerySupplierComponent },
  { path: 'login', component: LoginComponent },
  { path: 'maps', component: MapsComponent },
  { path: 'country', component: CountryInfoComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'service-info/:id', component: ServiceInfoComponent },
  { path: 'service-info-supplier/:id', component: ServiceInfoSupplierComponent },
  { path: '**', redirectTo: 'login' }, // Ruta comodín para rutas no encontradas

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
