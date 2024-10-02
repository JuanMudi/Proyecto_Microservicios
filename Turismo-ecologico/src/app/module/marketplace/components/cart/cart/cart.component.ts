import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cartItems: any[] = [];
  constructor(private router: Router) {}
  ngOnInit() {
    // Recuperar el carrito de localStorage
    this.cartItems = JSON.parse(localStorage.getItem('cart') || '[]');
}
goBack() {
  this.router.navigate(['']);  // Aquí navega a la ruta de inicio (listado de servicios)
}
}
