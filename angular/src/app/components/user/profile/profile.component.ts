import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { User } from '../../../models/user.model';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { UserService } from '../../../services/api/user.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NgIf, FormsModule, CardModule, ButtonModule, InputTextModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent {
  profile!: User;
  editedProfile = { ...this.profile };
  editMode = false;

  constructor(
    private keycloakService: KeycloakService,
    private userServide: UserService
  ) {}

  ngOnInit(): void {
    const userId = this.keycloakService.getUserId();
    console.log("key cloack user id ", userId);
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

  editProfile() {
    this.editMode = true;
    this.editedProfile = { ...this.profile }; 
    }

  saveProfile() {
    this.profile = { ...this.editedProfile }; 
    this.editMode = false;
    this.userServide.update(this.profile.id! ,this.profile);
  }
}
