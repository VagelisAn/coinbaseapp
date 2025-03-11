import { Component } from '@angular/core';
import { HeaderComponent } from './components/layout/header/header.component';
import { RouterModule } from '@angular/router';
import { authGuard } from './auth/gurd/auth.gurd';
import { roleGuard } from './auth/role/role.gurd';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HeaderComponent, RouterModule],
  template: `<app-header></app-header>
  `
})
export class AppComponent {
  constructor() {
    console.log("Manual authGuard call:", authGuard());
    console.log("Manual roleGuard call:", roleGuard(['admin'])());
  }
 }
