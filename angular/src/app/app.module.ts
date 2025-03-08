import { APP_INITIALIZER, NgModule, isDevMode } from '@angular/core';
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
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MaterialModule } from './material.module';
import { MaterialComponent } from './components/crypto/table/material/material.component';
import { ColorTextPipe } from './pipes/colorTextPipe';
import { AuthService } from './services/keycloak/auth.service';
import { KeycloakAngularModule } from 'keycloak-angular';

export function keycloakInitializer(authService: AuthService) {
  return () => authService.init();
}



@NgModule({ 
  declarations: [
        AppComponent,
        ListComponent,
        ChartComponent,
        MaterialComponent,
        ColorTextPipe
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
        KeycloakAngularModule,
        MaterialModule,
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
        MessageService,
        provideAnimationsAsync(),
        AuthService,
        {
          provide: APP_INITIALIZER,
          useFactory: keycloakInitializer,
          deps: [AuthService],
          multi: true,
        },
    ] })
export class AppModule { }
