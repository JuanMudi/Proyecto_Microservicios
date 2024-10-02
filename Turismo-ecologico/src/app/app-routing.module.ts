import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login/login.component';
import { RegisterComponent } from './pages/register/register/register.component';
import { ActivateAccountComponent } from './pages/active-account/activate-account/activate-account.component';
import { MainComponent } from './module/marketplace/pages/main/main/main.component';
import { authGuard } from './shared/auth/auth.guard';
import { CatalogComponent } from './module/marketplace/components/catalog/catalog/catalog.component';
import { DetailServiceComponent } from './module/marketplace/components/detail-service/detail-service/detail-service.component';
import { CartComponent } from './module/marketplace/components/cart/cart/cart.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'ecologic',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'activate-account',
    component: ActivateAccountComponent
  },

  {
    path: '',
    component: MainComponent,
    canActivate: [authGuard],
  },
   
        {
            path: '',
            component: CatalogComponent,
            canActivate: [authGuard]
        },
        { path: 'service/:id', 
          component: DetailServiceComponent ,
          canActivate: [authGuard]
        },
        { path: 'cart', 
          component: CartComponent,
          canActivate: [authGuard] }
    

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
