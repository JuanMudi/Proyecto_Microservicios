import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { authInterceptor } from './shared/interceptor/auth.interceptor';
import { LoginComponent } from './pages/login/login/login.component';
import { RegisterComponent } from './pages/register/register/register.component';
import { ActivateAccountComponent } from './pages/active-account/activate-account/activate-account.component';
import { FormsModule } from '@angular/forms';
import {CodeInputModule} from 'angular-code-input';
import { MainComponent } from './module/marketplace/pages/main/main/main.component';
import { CatalogComponent } from './module/marketplace/components/catalog/catalog/catalog.component';
import { DetailServiceComponent } from './module/marketplace/components/detail-service/detail-service/detail-service.component';
import { CartComponent } from './module/marketplace/components/cart/cart/cart.component';



export function kcFactory(kcService: KeycloakService){
  return () => kcService.init()

}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,
    MainComponent,
    CatalogComponent,
    DetailServiceComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CodeInputModule
  ],
  providers: [
   HttpClient,
   {
    provide: HTTP_INTERCEPTORS,
    useClass: authInterceptor,
    multi: true
   },
    
    {
    provide: APP_INITIALIZER,
    deps:[KeycloakService],
    useFactory: kcFactory,
    multi: true
   }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
