import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

export const authGuard = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
  | boolean
  | UrlTree
  | Observable<boolean | UrlTree>
  | Promise<boolean | UrlTree> => {
  
  const keycloak = inject(KeycloakService);

  return new Promise(async (resolve) => {
    const isAuthenticated = await keycloak.isLoggedIn();

    if (!isAuthenticated) {
        console.log("Please log in");
      keycloak.login();
      resolve(false);
    }

    // Έλεγχος για ρόλους
    const requiredRoles = route.data['roles'] as string[];
    console.log("requiredRoles ", requiredRoles, " key ", keycloak.getUserRoles());
    if (requiredRoles && requiredRoles.length > 0) {
        
      const hasAccess = requiredRoles.some((role) => keycloak.isUserInRole(role));

      if (!hasAccess) {
        resolve(false);
      }
    }

    resolve(true);
  });
};
