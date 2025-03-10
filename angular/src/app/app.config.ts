import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { MessageService } from 'primeng/api';
import Material from '@primeng/themes/material';
import { providePrimeNG } from 'primeng/config';
import { KeycloakService } from './services/keycloak/keycloak.service';


// Δημιουργία instance της υπηρεσίας Keycloak
const keycloakService = new KeycloakService();

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), 
    provideHttpClient(),
    provideAnimations(),
    providePrimeNG({
      theme: {
        preset: Material,
        options: {
          prefix: 'p',
          darkModeSelector: '.dark-theme'
        }
      },
      ripple: true
    }),
    MessageService,
    // Εδώ προσθέτεις το instance του KeycloakService
    { provide: KeycloakService, useValue: keycloakService }
  ]
};
