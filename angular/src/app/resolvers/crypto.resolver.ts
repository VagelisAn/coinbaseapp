// src/app/resolvers/crypto.resolver.ts
import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { CryptoService } from '../services/crypto.service';
import { loadAllCryptos } from '../store/crypto.actions';

@Injectable({
  providedIn: 'root',
})
export class CryptoResolver implements Resolve<any> {

  constructor(private cryptoService: CryptoService, private store: Store) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    this.store.dispatch(loadAllCryptos());
    console.log("loadcry")
    return this.cryptoService.getAllCryptos();
  }
}
