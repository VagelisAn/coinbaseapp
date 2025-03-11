import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from '../../services/keycloak/keycloak.service';

export const roleGuard = (allowedRoles: string[]) => {
  return () => {
    const keycloakService = inject(KeycloakService);
    const router = inject(Router);

    const userRoles = keycloakService.getClientRoles() || [];

    console.log("Checking roles...");
    console.log("User Roles:", userRoles);
    console.log("Allowed Roles:", allowedRoles);

    if (!allowedRoles.some(role => userRoles.includes(role))) {
      console.warn("Access denied! Redirecting to /unauthorized");
      router.navigate(['/unauthorized']);
      return false;
    }

    console.log("Access granted!");
    return true;
  };
};
