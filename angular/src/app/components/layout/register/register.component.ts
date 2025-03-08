import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/api/user.service';
import { MessageService } from 'src/app/services/mesage/message.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private messageService: MessageService
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
      this.userService
        .create(this.registerForm.value)
        .subscribe({
          next: () => {
            this.messageService.showSuccess('Owner created successfully!');
          },
          error: (error) => {
            if (error.error && error.error.errors && error.error.errors.length > 0) {
              const errorMessage = error.error.errors[0]; 
              this.messageService.showError(errorMessage);
            }
          }
        });
        
    }
  }

  
}
