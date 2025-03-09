import { Component } from '@angular/core';
import { NotificationService } from '../../services/messages/messeges.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../services/api/user.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  providers: [UserService],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private notificationService: NotificationService
  ) {}

  ngOnInit() {
    this.registerForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobile: ['', Validators.required],
      phone: [''],
      address: [''],
      postcode: [''],
    });
  }

  onRegister() {
    if (this.registerForm.valid) {
      this.userService.create(this.registerForm.value).subscribe({
        next: () => {
          this.notificationService.showSuccess(
            'Success!',
            'Owner created successfully!'
          );
        },
        error: (error: any) => {
          if (
            error.error &&
            error.error.errors &&
            error.error.errors.length > 0
          ) {
            const errorMessage = error.error.errors[0];
            this.notificationService.showError('Error!!', errorMessage);
          }
        },
      });
    }
  }
}
