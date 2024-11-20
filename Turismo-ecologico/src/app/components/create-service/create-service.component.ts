import { Component } from '@angular/core';

@Component({
  selector: 'app-create-service',
  templateUrl: './create-service.component.html',
  styleUrl: './create-service.component.css'
})
export class CreateServiceComponent {
  newService = {
    title: '',
    description: '',
    category: 'transporte', // Valor predeterminado
    departureDate: '',
    arrivalDate: '',
    route: '',
    transportType: '',
    operatingDays: '',
    operatingHours: '',
    tourRoute: '',
    menu: '',
    availableRooms: 0
  };

  onCategoryChange() {
    // Resetear los campos específicos de la categoría seleccionada
    if (this.newService.category !== 'alimentacion') {
      this.newService.menu = '';
    }
    if (this.newService.category !== 'alojamiento') {
      this.newService.availableRooms = 0;
    }
  }

  submitService() {
    console.log('Servicio Creado:', this.newService);
    // Aquí puedes agregar la lógica para enviar el servicio al backend
    alert('Servicio creado con éxito');
  }
    // Función para limpiar el formulario
    clearForm() {
      this.newService = {
        title: '',
        description: '',
        category: '',
        departureDate: '',
        arrivalDate: '',
        route: '',
        transportType: '',
        operatingDays: '',
        operatingHours: '',
        tourRoute: '',
        menu: '',
        availableRooms: 0
      };
    }
}
