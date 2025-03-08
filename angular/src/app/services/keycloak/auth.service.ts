import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private keycloak: KeycloakService) {}

  // Initialize Keycloak
  async init(): Promise<boolean> {
    try {
      await this.keycloak.init({
        config: {
          url: 'http://localhost:8080',
          realm: 'CoinBase',
          clientId: 'coin-base',
        },
        initOptions: {
          onLoad: 'login-required', // Redirects to login if not authenticated
          checkLoginIframe: false,
        },
      });
      return true;
    } catch (error) {
      console.error('Keycloak initialization failed', error);
      return false;
    }
  }

  // Get authenticated user info
  getUserProfile() {
    return this.keycloak.loadUserProfile();
  }

  // Get authentication token
  getToken(): Promise<string> {
    return this.keycloak.getToken();
  }

  // Check if the user has a specific role
  hasRole(role: string): boolean {
    return this.keycloak.isUserInRole(role);
  }

  // Logout
  logout() {
    this.keycloak.logout();
  }
}