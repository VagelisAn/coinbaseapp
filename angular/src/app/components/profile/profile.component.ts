import { Component } from '@angular/core';
import { UserService } from '../../services/api/user.service';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { User } from '../../models/user.model';
import { CommonModule, NgIf } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule, DividerModule, NgIf],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent {
  profile!: User;

  constructor(
    private keycloakService: KeycloakService,
    private userServide: UserService
  ) {}

  ngOnInit(): void {
    const userId = this.keycloakService.getUserId();
    if (userId) {
      // Παράδειγμα URL, αντικατάστησέ το με το δικό σου endpoint
      this.userServide.getByKeycloakId(userId).subscribe({
        next: (data) => {
          this.profile = data;
          console.log('User data:', data);
        },
        error: (err) => console.error('Error fetching user data:', err),
      });
    } else {
      console.error('User ID not found.');
    }
  }
}
