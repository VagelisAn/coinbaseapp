import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private keycloak: Keycloak.KeycloakInstance;
  private initialized = false;

  constructor() {
    this.keycloak = new Keycloak({
      url: 'http://localhost:8080',
      realm: 'CoinBase',
      clientId: 'coin-base',
    });
  }

  async initKeycloak(): Promise<boolean> {

    console.log(" ", this.initialized)

    if (this.initialized) {
      return true; // Αν έχει ήδη γίνει init, μην το ξανατρέξεις
    }
    

    try {
      const authenticated = await this.keycloak.init({
        onLoad: 'login-required',
        checkLoginIframe: false // Βοηθάει στο να μην κάνει re-init αυτόματα
      });
      console.log("!!! ", authenticated)
      if (authenticated) {
        console.log("!!! ", JSON.stringify(this.keycloak?.tokenParsed?.resource_access?.['coin-base']?.roles!))
        localStorage.setItem('kc-token', this.keycloak.token!); // Αποθήκευση token
        localStorage.setItem('kc-refresh-token', this.keycloak.refreshToken!);
        localStorage.setItem('kc-user-roles', JSON.stringify(this.keycloak?.tokenParsed?.resource_access?.['coin-base']?.roles!));
        this.initialized = true;
      }
      console.log(" ", this.initialized, this.isAuthenticated)
      
      return authenticated;
    } catch (error) {
      console.error('Keycloak authentication failed', error);
      return false;
    }
  }

  isAuthenticated(): boolean {
    return this.keycloak.authenticated ?? false;
  }

  /**
   * Παίρνει το αποθηκευμένο ή το live Token.
   */
  getToken(): string | null {
    return this.keycloak?.token || localStorage.getItem('kc-token');
  }

  /**
   * Παίρνει τους ρόλους του χρήστη από το `tokenParsed`.
   */
  getClientRoles(): string[] {
    console.log("Eyd ", JSON.parse(localStorage.getItem('kc-user-roles')!));
    return this.keycloak?.tokenParsed?.resource_access?.['coin-base']?.roles || JSON.parse(localStorage.getItem('kc-user-roles') || '[]');
  }

  getUserName(): string | null {
    return this.keycloak?.tokenParsed?.['preferred_username'] || null;
  }

  getUserEmail(): string | null {
    return this.keycloak?.tokenParsed?.['email'] || null;
  }


  /**
   * Αποσυνδέει τον χρήστη και καθαρίζει τα stored tokens.
   */
  logout(): void {
    localStorage.removeItem('kc-token');
    localStorage.removeItem('kc-refresh-token');
    this.keycloak.logout();
  }

}
