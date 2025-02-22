import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/layout/home/home.component';
import { ChartComponent } from './components/crypto/chart/chart.component';
import { ListComponent } from './components/crypto/list/list.component';
import { CryptoResolver } from './resolvers/crypto.resolver';

const routes: Routes = [
{ path: 'home', component: HomeComponent,  
  resolve: {
  cryptos: CryptoResolver, 
          }
},
{ path: '', redirectTo: '/home', pathMatch: 'full' },
{ path: 'market', component: ListComponent },
{ path: 'charts', component: ChartComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
