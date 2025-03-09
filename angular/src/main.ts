import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { KeycloakService } from './app/services/keycloak/keycloak.service';

const keycloakService = new KeycloakService();

keycloakService.initKeycloak().then((authenticated) => {
  if (authenticated) {
    console.log('✅ Keycloak authenticated successfully');
    bootstrapApplication(AppComponent, appConfig)
      .catch(err => console.error('Bootstrap error:', err));
  } else {
    console.error('❌ Authentication failed, cannot load the app');
  }
});
