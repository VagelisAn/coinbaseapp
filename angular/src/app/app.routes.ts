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
import { PageComponent } from './components/admin/page/page.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },

  { 
    path: 'admin',
    component: AdminComponent,
    canActivate: [authGuard, roleGuard(['admin'])],
    children: [
      { path: 'page', component: PageComponent },
      { path: 'users', component: UserListComponent }
    ]
  },

  { 
    path: 'user',
    component: UserComponent,
    canActivate: [authGuard, roleGuard(['user'])],
    children: [
      { path: 'chart', component: ChartComponent },
      { path: 'list', component: ListComponent },
      { path: 'profile', component: ProfileComponent }
    ]
  },

  { path: 'register', component: RegisterComponent },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: '' }
];