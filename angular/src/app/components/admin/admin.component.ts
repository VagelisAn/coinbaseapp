import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { TableModule } from 'primeng/table';
import { KeycloakService } from '../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-admin',
  imports:  [CommonModule, TableModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
  private keycloakService = inject(KeycloakService);
  userInfo = signal([
    {
      name: this.keycloakService.getUserName(),
      email: this.keycloakService.getUserEmail(),
      roles: this.keycloakService.getClientRoles(),
    },
  ]);
}
