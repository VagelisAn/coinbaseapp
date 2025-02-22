import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PrimeNgModule } from './prime.ng.module';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { cryptoReducer } from './store/crypto.reducer';
import { CryptoEffects } from './store/crypto.effects';
import { ListComponent } from './components/crypto/list/list.component';
import { ChartComponent } from './components/crypto/chart/chart.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { LayoutModules } from './components/layout/layout.module';
import { MessageService } from 'primeng/api';






@NgModule({ 
  declarations: [
        AppComponent,
        ListComponent,
        ChartComponent,
    ],
    bootstrap: [AppComponent], 
    exports: [ ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        FormsModule,
        PrimeNgModule,
        LayoutModules,
        StoreModule.forRoot({crypto: cryptoReducer }),
        EffectsModule.forRoot([CryptoEffects]),
        StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: !isDevMode() }),
        NgxEchartsModule.forRoot({
          echarts: () => import('echarts'), // Lazy-load ECharts
        })
      ], 
      providers: [
        provideHttpClient(withInterceptorsFromDi()),
        MessageService
    ] })
export class AppModule { }
