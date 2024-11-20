import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  private apiUrl = 'https://restcountries.com/v3.1/name/';

  constructor(private http: HttpClient) {}

  // Método para obtener información de un país
  getCountryInfo(countryName: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}${countryName}`);
  }
}
