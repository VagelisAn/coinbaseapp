import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { CryptoService } from '../services/crypto.service';
import * as CryptoActions from './crypto.actions';
import { catchError, map, mergeMap, of } from 'rxjs';

@Injectable()
export class CryptoEffects {

  constructor(private actions$: Actions, private cryptoService: CryptoService) {}

  loadAllCryptos$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CryptoActions.loadAllCryptos),
      mergeMap((_) =>
        this.cryptoService.getAllCryptos().pipe(
          map((cryptos) => CryptoActions.loadCryptosSuccess({ cryptos })),
          catchError((error) => of(CryptoActions.loadCryptosFailure({ error: error.message })))
        )
      )
    )
  );
  
  loadCryptos$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CryptoActions.loadCryptos),
      mergeMap((action) =>
        this.cryptoService.getCryptos(action.page, action.perPage).pipe(
          map((cryptos) => CryptoActions.loadCryptosSuccess({ cryptos })),
          catchError((error) => of(CryptoActions.loadCryptosFailure({ error: error.message })))
        )
      )
    )
  );

  
}