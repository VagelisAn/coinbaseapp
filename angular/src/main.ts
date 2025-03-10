import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { MessageService } from 'primeng/api';
import Material from '@primeng/themes/material';
import { providePrimeNG } from 'primeng/config';
import { KeycloakService } from './app/services/keycloak/keycloak.service';
import { routes } from './app/app.routes';

const keycloakService = new KeycloakService();

keycloakService.initKeycloak().then((authenticated) => {
  if (authenticated) {
    console.log('✅ Keycloak authenticated successfully');
    
    bootstrapApplication(AppComponent, {
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
    }).catch(err => console.error('Bootstrap error:', err));

  } else {
    console.error('❌ Authentication failed, cannot load the app');
  }
});
