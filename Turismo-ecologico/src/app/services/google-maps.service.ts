import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GoogleMapsService {
  private googleMapsLoaded = false;

  constructor() {}

  loadGoogleMaps(apiKey: string): Promise<void> {
    if (this.googleMapsLoaded) {
      return Promise.resolve(); // Ya está cargado
    }

    return new Promise((resolve, reject) => {
      const script = document.createElement('script');
      script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyBLSCP__X3GE7iXp91VmaZoO_HZwYPRy6I`;
      script.async = true;
      script.defer = true;

      script.onload = () => {
        this.googleMapsLoaded = true;
        resolve();
      };

      script.onerror = (error) => {
        reject(error);
      };

      document.body.appendChild(script);
    });
  }
}
