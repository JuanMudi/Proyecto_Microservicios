import { Component, OnInit } from '@angular/core';
import { GoogleMapsService } from '../../services/google-maps.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrl: './maps.component.css'
})
export class MapsComponent implements OnInit {
  latitude = 4.711; // Bogotá, Colombia
  longitude = -74.072;

  constructor(private googleMapsService: GoogleMapsService, private router: Router) {}

  ngOnInit(): void {
    this.googleMapsService
      .loadGoogleMaps('AIzaSyBLSCP__X3GE7iXp91VmaZoO_HZwYPRy6I')
      .then(() => {
        this.initMap();
      })
      .catch((error) => {
        console.error('Error loading Google Maps', error);
      });
  }

  initMap(): void {
    const location = { lat: this.latitude, lng: this.longitude };

    const map = new google.maps.Map(document.getElementById('map') as HTMLElement, {
      center: location,
      zoom: 12,
    });

    new google.maps.Marker({
      position: location,
      map: map,
      title: 'Aquí está tu punto',
    });
  }
  goBack(): void {
    this.router.navigate(['galery-supplier']);
  } 
}