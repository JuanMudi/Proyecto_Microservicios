import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { AuthResponse } from '../../model/authresponse';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  message: string = '';

  constructor(private fb: FormBuilder, private router: Router, private authService: LoginService) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]], // Validación básica para username
      password: ['', [Validators.required, Validators.minLength(6)]], // Validación para password
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.value;

      this.authService.login(username, password).subscribe({
        next: (response: AuthResponse) => {
          if (response.access_token) {
            this.message = 'Login exitoso';
            this.validateUser(username, response); // Pasamos la respuesta para validar usuario
          }
        },
        error: (error) => {
          this.message = 'Error al iniciar sesión. Verifica tus credenciales.';
          console.error(error);
        }
      });
    } else {
      this.message = 'Por favor, completa todos los campos correctamente.';
    }
  }

  validateUser(username: string, authResponse: AuthResponse): void {
    if (username === 'nikoresu') {
      this.message = `Bienvenido, cliente. Token: ${authResponse.access_token}`;
      this.router.navigate(['/galery-client']); // Redirige a la galería del cliente
    } else if (username === 'carlosB') {
      this.message = `Bienvenido, proveedor. Token: ${authResponse.access_token}`;
      this.router.navigate(['/provider-dashboard']); // Redirige al dashboard del proveedor
      this.router.navigate(['/galery-supplier']); // Redirige a la galería del cliente
    } else {
      this.message = 'Usuario no reconocido.';
    }
  }

  redirectToRegister(): void {
    this.router.navigate(['/register']);
  }
}