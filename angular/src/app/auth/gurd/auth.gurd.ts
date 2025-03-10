import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from '../../services/keycloak/keycloak.service';

export const authGuard = () => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);
  if (!keycloakService.isAuthenticated()) {
    router.navigate(['/login']);
    return false;
  }
  return true;
};