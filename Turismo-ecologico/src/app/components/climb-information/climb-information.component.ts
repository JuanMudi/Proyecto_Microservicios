import { Component, OnInit } from '@angular/core';
import { WeatherService } from '../../services/weather.service';

@Component({
  selector: 'app-climb-information',
  templateUrl: './climb-information.component.html',
  styleUrl: './climb-information.component.css'
})
export class ClimbInformationComponent implements OnInit {
  city: string = 'Bogotá'; // Ciudad predeterminada
  startDate: string = new Date().toISOString().split('T')[0]; // Fecha de hoy
endDate: string = ''; // Fecha seleccionada por el usuario

  weatherData: any[] = []; // Datos del clima filtrados
  errorMessage: string = ''; // Mensaje de error

  constructor(private weatherService: WeatherService) {}

  ngOnInit(): void {
    this.getWeather();
  }

  // Método para obtener el clima
  getWeather(): void {
    this.weatherService.getWeather(this.city).subscribe({
      next: (data) => {
        this.filterWeatherData(data.list);
      },
      error: (error) => {
        this.errorMessage = 'Error al obtener los datos del clima.';
      },
    });
  }

  filterWeatherData(data: any[]): void {
    const start = new Date(this.startDate).getTime(); // Convierte la fecha inicial a milisegundos
    const end = this.endDate ? new Date(this.endDate).getTime() : Infinity; // Convierte la fecha final si existe
  
    this.weatherData = data.filter((item) => {
      const itemDate = new Date(item.dt_txt).getTime(); // Fecha de cada registro
      return itemDate >= start && itemDate <= end; // Filtra el rango
    });
  
    // Mensaje si no hay datos para el rango seleccionado
    if (this.weatherData.length === 0) {
      this.errorMessage = 'No hay datos disponibles para este rango de fechas.';
    } else {
      this.errorMessage = ''; // Limpia el mensaje de error si hay datos
    }
  }
  
}
