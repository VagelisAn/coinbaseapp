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
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, RouterModule, MenubarModule, ButtonModule, SidebarModule, PanelMenuModule],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  private keycloakService = inject(KeycloakService);
  menuVisible = true;
  isAuthenticated = this.keycloakService.isAuthenticated();
  username = this.keycloakService.getUserName();
  
  menuItems: MenuItem[] = [
    { label: 'Menu', icon: 'pi pi-bars', command: () => this.menuVisible = !this.menuVisible }
  ];

  sideMenu: MenuItem[] = this.getUserMenu();

  logout() {
    this.keycloakService.logout();
  }

  getUserMenu(): MenuItem[] {
    const roles = this.keycloakService.getClientRoles();
    console.log("Roles ", roles)
    if (roles.includes('admin')) {
      return [
        { label: 'Dashboard', icon: 'pi pi-home', routerLink: ['/dashboard'] },
        { label: 'Users', icon: 'pi pi-users', routerLink: ['/admin'] }
      ];
    } else {
      return [
        { label: 'Profile', icon: 'pi pi-user', routerLink: ['/profile'] },
        { label: 'Settings', icon: 'pi pi-cog', routerLink: ['/settings'] }
      ];
    }
  }
}
