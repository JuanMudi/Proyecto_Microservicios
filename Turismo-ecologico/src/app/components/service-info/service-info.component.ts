import { Component, OnInit } from '@angular/core';
import { Gallery } from '../../model/gallery';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-service-info',
  templateUrl: './service-info.component.html',
  styleUrls: ['./service-info.component.css']
})
export class ServiceInfoComponent implements OnInit {
    service!: Gallery; // Detalle del servicio seleccionado
    question: string = ''; // Campo para almacenar la pregunta del cliente
    questions: string[] = []; // Lista de preguntas
    characterCount: number = 0; // Contador de caracteres
  
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
          imageUrl: 'https://www.transportesdelecu.com/uploads/2/7/0/3/27031343/5247131_orig.jpg' 
      }
    ];
  
    // Simulación de calificaciones de usuario
    ratings = [
      { userImage: 'https://www.w3schools.com/w3images/avatar2.png', stars: 5, comment: 'Excelente servicio, muy recomendable.' },
      { userImage: 'https://www.w3schools.com/w3images/avatar5.png', stars: 4, comment: 'Buen servicio, pero la comida podría mejorar.' }
    ];
  
    // Datos para la nueva calificación
    newRating = {
      stars: 1,
      comment: ''
    };
  
    constructor(private route: ActivatedRoute, private router: Router) {}
  
    ngOnInit(): void {
      this.route.params.subscribe(params => {
        const serviceId = params['id'];
        this.service = this.services.find(s => s.id === +serviceId) || this.services[0];
      });
    }
  
    updateCharacterCount() {
      this.characterCount = this.question.length;
    }
  
    submitQuestion() {
      if (this.question.trim().length > 0 && this.characterCount <= 500) {
        this.questions.push(this.question);
        this.question = '';
        this.characterCount = 0;
        Swal.fire('Pregunta enviada!', '', 'success');
      } else {
        Swal.fire('Error', 'Por favor, asegúrate de que la pregunta no esté vacía y tenga menos de 500 caracteres.', 'error');
      }
    }
  
    submitRating() {
      if (this.newRating.comment.trim().length > 0 && this.newRating.stars > 0) {
        this.ratings.push({ 
          userImage: 'https://www.w3schools.com/w3images/avatar1.png', // Imagen predeterminada del usuario
          stars: this.newRating.stars, 
          comment: this.newRating.comment 
        });
        this.newRating = { stars: 1, comment: '' }; // Limpiar el formulario de calificación
        Swal.fire('Gracias por tu calificación!', '', 'success');
      } else {
        Swal.fire('Error', 'Por favor, asegúrate de que la calificación y el comentario no estén vacíos.', 'error');
      }
    }
  }