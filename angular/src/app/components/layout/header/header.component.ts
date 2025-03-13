import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import { SidebarModule } from 'primeng/sidebar';
import { PanelMenuModule } from 'primeng/panelmenu';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule, MenubarModule, ButtonModule, SidebarModule, PanelMenuModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  private keycloakService = inject(KeycloakService);
  menuVisible = true;
  isAuthenticated = this.keycloakService.isAuthenticated();
  username = this.keycloakService.getUserName();
  
  menuItems: MenuItem[] = [];
  sideMenu: MenuItem[] = [];

  ngOnInit() {
    this.menuItems = [
      { label: 'Menu', icon: 'pi pi-bars', command: () => this.menuVisible = !this.menuVisible }
    ];

    this.sideMenu = this.getUserMenu();
  }

  logout() {
    this.keycloakService.logout();
  }

  getUserMenu(): MenuItem[] {
    const roles = this.keycloakService.getClientRoles();
    if (roles.includes('admin')) {
      return [
        { label: 'Admin Home', routerLink: '/admin/page' },
        { label: 'Users', routerLink: '/admin/users' }
      ];
    } else if (roles.includes('user')) {
      return [
        { label: 'User Home', routerLink: '/user' },
        { label: 'Profile', routerLink: '/user/profile' },
        { label: 'List', routerLink: '/user/list' },
        { label: 'Chart', routerLink: '/user/chart' }
      ];
    } else {
      return [
        { label: 'Home', routerLink: 'home' }
      ]; // ✅ Default option in case no role matches
    }
  }
}