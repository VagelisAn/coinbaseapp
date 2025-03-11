import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { TableModule } from 'primeng/table';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin',
  imports:  [CommonModule, TableModule, RouterModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

}
