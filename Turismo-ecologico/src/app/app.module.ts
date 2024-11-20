import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { GaleryClientComponent } from './components/galery-client/galery-client.component';
import { GalerySupplierComponent } from './components/galery-supplier/galery-supplier.component';
import { ServiceInfoComponent } from './components/service-info/service-info.component';
import { CartComponent } from './components/cart/cart.component';
import { ServiceInfoSupplierComponent } from './components/service-info-supplier/service-info-supplier.component';
import { MapsComponent } from './components/maps/maps.component';
import { CountryInfoComponent } from './components/country-info/country-info.component';
import { ClimbInformationComponent } from './components/climb-information/climb-information.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateServiceComponent } from './components/create-service/create-service.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    GaleryClientComponent,
    GalerySupplierComponent,
    ServiceInfoComponent,
    CartComponent,
    ServiceInfoSupplierComponent,
    MapsComponent,
    CountryInfoComponent,
    ClimbInformationComponent,
    CreateServiceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
