import { Routes } from '@angular/router';
import { authGuard } from './auth/gurd/auth.gurd';
import { roleGuard } from './auth/role/role.gurd';
import { HomeComponent } from './components/layout/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { RegisterComponent } from './components/register/register.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { LayoutComponent } from './components/layout/layout/layout.component';
import { ChartComponent } from './components/crypto/chart/chart.component';


export const routes: Routes = [
  { path: 'home', component: ChartComponent },
  { path: 'admin', component: AdminComponent, canActivate: [authGuard, roleGuard(['admin'])] },
  { path: 'Dashboard', component: RegisterComponent },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: '' }
];
