import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from '../../services/keycloak/keycloak.service';


export const roleGuard = (allowedRoles: string[]) => () => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);
  // const userRoles = keycloakService.getRoles();
  const userRoles = keycloakService.getClientRoles();

  console.log("userRoles ", userRoles, " allowedRoles ", allowedRoles)

  if (!allowedRoles.some((role) => userRoles.includes(role))) {
    router.navigate(['/unauthorized']);
    return false;
  }
  return true;
};
