import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { RegisterRequest } from '../../model/register-request';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup;
  userType: string = 'cliente'; // Por defecto es cliente

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: LoginService
  ) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      edad: ['', [Validators.required, Validators.min(1)]],
      descripcion: ['', Validators.required],
      paginaWeb: [''], // Solo proveedor
    });
  }

  onUserTypeChange(): void {
    if (this.userType === 'proveedor') {
      this.registerForm.get('paginaWeb')?.setValidators([Validators.required]);
    } else {
      this.registerForm.get('paginaWeb')?.clearValidators();
    }
    this.registerForm.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const { username, password, nombre, apellido, edad, descripcion, paginaWeb } = this.registerForm.value;

      const registerData: RegisterRequest = {
        username,
        firstName: nombre,
        lastName: apellido,
        email: `${username}@mail.com`, // Generar un email básico basado en el username
        emailVerified: false,
        enabled: true,
        password,
        age: edad,
        bio: descripcion,
        webPage: this.userType === 'proveedor' ? paginaWeb : null,
      };

      this.authService.register(registerData).subscribe({
        next: () => {
          Swal.fire({
            title: 'Usuario creado',
            text: `El usuario ${username} ha sido registrado con éxito.`,
            icon: 'success',
            confirmButtonText: 'Aceptar'
          }).then(() => {
            this.router.navigate(['/login']); // Redirigir al login
          });
        },
        error: (err) => {
          console.error(err);
          Swal.fire({
            title: 'Error',
            text: 'Hubo un problema al registrar el usuario. Intente nuevamente.',
            icon: 'error',
            confirmButtonText: 'Aceptar'
          });
        },
      });
    } else {
      Swal.fire({
        title: 'Formulario inválido',
        text: 'Por favor complete todos los campos requeridos.',
        icon: 'warning',
        confirmButtonText: 'Aceptar'
      });
    }
  }

  clearForm(): void {
    this.registerForm.reset();
    this.userType = 'cliente'; // Restablecer a cliente por defecto
  }

  redirectToLogin(): void {
    this.router.navigate(['/login']);
  }
}