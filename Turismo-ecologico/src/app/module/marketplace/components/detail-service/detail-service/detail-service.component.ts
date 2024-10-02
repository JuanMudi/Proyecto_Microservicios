import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Gallery } from '../../../../../services/models/gallery';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detail-service',
  templateUrl: './detail-service.component.html',
  styleUrl: './detail-service.component.css'
})
export class DetailServiceComponent implements OnInit {
  service!: Gallery; // Detalle del servicio seleccionado
    question: string = ''; // Campo para almacenar la pregunta del cliente
    questions: string[] = []; // Lista de preguntas

    // Simulación de servicios
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

    constructor(private route: ActivatedRoute, private router: Router) {}

    ngOnInit(): void {
        const serviceId = +this.route.snapshot.paramMap.get('id')!; // Obtener el ID del servicio de la URL
        this.service = this.services.find(s => s.id === serviceId)!; // Buscar el servicio correspondiente
    }

    submitQuestion() {
        Swal.fire({
            title: '¿Está seguro que quiere enviar la pregunta?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí, enviar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                if (this.question) {
                    this.questions.push(this.question);
                    this.question = ''; // Limpiar el campo de entrada
                    Swal.fire('¡Enviado!', 'Su pregunta ha sido enviada.', 'success'); // Mensaje de éxito
                }
            }
        });
    }

    buyProduct() {
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
              cart.push(this.service);
              localStorage.setItem('cart', JSON.stringify(cart));

              Swal.fire('¡Compra Exitosa!', `El producto ${this.service.title} fue comprado de manera exitosa.`, 'success');
          }
      });
  }
    goBack() {
      this.router.navigate(['/']); // Redirigir a la ruta raíz (o la ruta de tu listado de servicios)
  }
}
