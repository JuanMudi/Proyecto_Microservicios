import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js'
import { UserProfile } from './user-profile';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak: Keycloak | undefined;
  private _profile: UserProfile | undefined;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:9000',
        realm: 'landmates',
        clientId: 'bsn'
      });
    }
    return this._keycloak;
  }

  get profile(): UserProfile | undefined {
    return this._profile
  }

  constructor() { }

  async init() {
   
    const authenticated: boolean = await this.keycloak?.init({
      onLoad: 'login-required',
      checkLoginIframe: false

    });

    if (authenticated) {
      this._profile = (await this.keycloak?.loadUserProfile())as UserProfile;
      this._profile.token = this.keycloak?.token;
      console.log('Authenticade: ' + this._profile.token)
    }
  }

  login(){
    return this.keycloak?.login();
  }

  logout(){
    return this.keycloak?.logout({redirectUri: 'http:localhost4200'});
  }
}
