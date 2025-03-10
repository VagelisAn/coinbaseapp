import { Routes } from '@angular/router';
import { authGuard } from './auth/gurd/auth.gurd';
import { roleGuard } from './auth/role/role.gurd';
import { HomeComponent } from './components/layout/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { RegisterComponent } from './components/register/register.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { LayoutComponent } from './components/layout/layout/layout.component';
import { ChartComponent } from './components/crypto/chart/chart.component';
import { ListComponent } from './components/crypto/list/list.component';
import { ProfileComponent } from './components/profile/profile.component';


export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminComponent, canActivate: [authGuard, roleGuard(['admin'])] },
  { path: 'chart', component: ChartComponent, canActivate: [authGuard, roleGuard(['user'])] },
  { path: 'list', component: ListComponent, canActivate: [authGuard, roleGuard(['user'])] },
  { path: 'profile', component: ProfileComponent, canActivate: [authGuard, roleGuard(['user'])] },
  { path: 'register', component: RegisterComponent },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: '' }
];
