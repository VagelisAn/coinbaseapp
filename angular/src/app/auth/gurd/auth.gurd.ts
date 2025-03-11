import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from '../../services/keycloak/keycloak.service';

export const authGuard = () => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);

  console.log("Checking authentication...");

  if (!keycloakService.isAuthenticated()) {
    console.warn("User not authenticated! Redirecting...");
    router.navigate(['/login']);
    return false;
  }

  console.log("User authenticated!");
  return true;
};
