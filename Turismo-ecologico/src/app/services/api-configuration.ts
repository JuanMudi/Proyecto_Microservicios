import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiConfiguration {
  rootUrl: string = 'http://localhost:8088/api/v1';
}

/**
 * Parameters for `ApiModule.forRoot()`
 */
export interface ApiConfigurationParams {
  rootUrl?: string;
}