import { Component } from '@angular/core';
import { Gallery } from '../../../../../services/models/gallery';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrl: './catalog.component.css'
})
export class CatalogComponent {
  searchQuery: string = '';
  services: Gallery[] = [
      { 
          id: 1, 
          title: 'Alojamiento Ecológico', 
          description: 'Disfruta de una experiencia única en la naturaleza.', 
          category: 'Alojamiento',
          imageUrl: 'https://ovacen.com/wp-content/uploads/2020/08/hotel-lagrima-ecologico.jpg.webp' 
      },
      { 
          id: 2, 
          title: 'Comida Orgánica', 
          description: 'Saborea platos hechos con ingredientes frescos y locales.', 
          category: 'Alimentación',
          imageUrl: 'https://www.gob.mx/cms/uploads/article/main_image/26456/org1.jpg' 
      },
      { 
          id: 3, 
          title: 'Transporte Sostenible', 
          description: 'Viaja de manera responsable con nuestro transporte ecológico.', 
          category: 'Transporte',
          imageUrl: 'https://trenty.emesa-m30.es/wp-content/uploads/2023/12/Transporte_sostenible_1.jpg' 
      },
      { 
          id: 4, 
          title: 'Paseos Ecológicos', 
          description: 'Explora la naturaleza con nuestras excursiones guiadas.', 
          category: 'Paseos Ecológicos',
          imageUrl: 'https://www.awali.school/wp-content/uploads/2015/09/Caminata-2-600x768.jpg' 
      }
  ];

  constructor(private router: Router) {}

  get filteredServices() {
      return this.services.filter(service =>
          service.title.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
          service.description.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
  }
  buyProduct(service: any) {
    Swal.fire({
        title: '¿Está seguro que quiere comprar este producto?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, comprar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Almacenar el producto en localStorage
            let cart = JSON.parse(localStorage.getItem('cart') || '[]');
            cart.push(service);
            localStorage.setItem('cart', JSON.stringify(cart));

            Swal.fire('¡Compra Exitosa!', `El producto ${service.title} fue comprado de manera exitosa.`, 'success');
        }
    });
}
  goToDetail(id: number) {
      this.router.navigate(['/service', id]); // Navegar al componente de detalle con el ID del servicio
  }
}


