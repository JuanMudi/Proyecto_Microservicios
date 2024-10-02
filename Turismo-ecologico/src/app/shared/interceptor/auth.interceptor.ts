import { HttpHandler, HttpInterceptorFn, HttpEvent, HttpHeaders, HttpRequest, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { Injectable } from '@angular/core';


@Injectable()
export class authInterceptor implements HttpInterceptor  {
  constructor(
    private keycloakService: KeycloakService
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable < HttpEvent < unknown >> {
    const token = this.keycloakService.keycloak.token;

    if(token) {
      const authReq = request.clone({
        headers: new HttpHeaders({
          Authorization: `Bearer ${token}`
        })
      });
      return next.handle(authReq);
    }
    return next.handle(request);
  }
}