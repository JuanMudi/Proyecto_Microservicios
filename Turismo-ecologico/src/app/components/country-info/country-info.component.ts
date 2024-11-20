import { Component, Input, OnInit } from '@angular/core';
import { CountryService } from '../../services/country.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-country-info',
  templateUrl: './country-info.component.html',
  styleUrl: './country-info.component.css'
})
export class CountryInfoComponent implements OnInit {
  @Input() countryName: string = 'Colombia'; // Nombre del país por defecto
  countryData: any = null;  // Información del país
  errorMessage: string = ''; // Mensaje de error (si ocurre)

  constructor(private countryService: CountryService, private router: Router) {}

  ngOnInit(): void {
    this.getCountryData();
  }

  // Método para obtener información del país
  getCountryData(): void {
    this.countryService.getCountryInfo(this.countryName).subscribe({
      next: (data) => {
        this.countryData = data[0]; // Toma el primer resultado
      },
      error: (error) => {
        this.countryData = null;
        this.errorMessage = 'No se encontró información para ese país.';
      },
    });
  }

  // Método para redirigir a la galería del proveedor
  goBack(): void {
    this.router.navigate(['galery-supplier']);
  }
}