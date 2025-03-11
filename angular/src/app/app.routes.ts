import { Routes } from '@angular/router';
import { authGuard } from './auth/gurd/auth.gurd';
import { roleGuard } from './auth/role/role.gurd';
import { HomeComponent } from './components/layout/home/home.component';
import { AdminComponent } from './components/admin/admin/admin.component';
import { RegisterComponent } from './components/register/register.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { UserListComponent } from './components/admin/user-list/user-list.component';
import { UserComponent } from './components/user/user/user.component';
import { ChartComponent } from './components/user/chart/chart.component';
import { ListComponent } from './components/user/list/list.component';
import { ProfileComponent } from './components/user/profile/profile.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'unauthorized', component: UnauthorizedComponent },
  
  // Admin Routes
  { path: 'admin', component: AdminComponent, canActivate: [authGuard, roleGuard], data: { roles: ['admin'] } },
  { path: 'admin-users', component: UserListComponent, canActivate: [authGuard, roleGuard], data: { roles: ['admin'] } },

  // User Routes
  { path: 'user', component: UserComponent, canActivate: [authGuard, roleGuard], data: { roles: ['user'] } },
  { path: 'user-profile', component: ProfileComponent, canActivate: [authGuard, roleGuard], data: { roles: ['user'] } },
  { path: 'user-list', component: ListComponent, canActivate: [authGuard, roleGuard], data: { roles: ['user'] } },
  { path: 'user-chart', component: ChartComponent, canActivate: [authGuard, roleGuard], data: { roles: ['user'] } },

  { path: '**', redirectTo: '' }
];