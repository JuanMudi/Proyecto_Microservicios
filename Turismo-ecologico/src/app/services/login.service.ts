import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthResponse } from '../model/authresponse';
import { RegisterRequest } from '../model/register-request';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://35.224.154.86:8080/auth/login'; // URL del backend
  private apiRegisterUrl = 'http://35.224.154.86:8080/auth/register';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<AuthResponse> {
    const body = { username, password }; // Body con los campos esperados
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }); // Encabezados

    return this.http.post<AuthResponse>(this.apiUrl, body, { headers }).pipe(
      catchError(error => {
        console.error('Login error:', error);
        return throwError(() => new Error('Login failed'));
      })
    );
  }
  register(registerData: RegisterRequest): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.apiRegisterUrl, registerData, { headers }).pipe(
      catchError((error) => {
        console.error('Register error:', error);
        return throwError(() => new Error('Register failed'));
      })
    );
  }
}