import { createReducer, on } from '@ngrx/store';
import * as CryptoActions from './crypto.actions';

export interface CryptoState {
    cryptos: any[];
    error: string | null;
  }
  
  export const initialState: CryptoState = {
    cryptos: [],
    error: null,
  };
  
  export const cryptoReducer = createReducer(
    initialState,
    on(CryptoActions.loadCryptosSuccess, (state, { cryptos }) => ({
      ...state,
      cryptos,
      error: null,
    })),
    on(CryptoActions.loadCryptosFailure, (state, { error }) => ({
      ...state,
      error,
    }))
  );
  