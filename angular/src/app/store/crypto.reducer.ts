import { createReducer, on } from '@ngrx/store';
import * as CryptoActions from './crypto.actions';
import { initialState } from './crypto.state';

export const cryptoReducer = createReducer(
  initialState,
  on(CryptoActions.loadCryptosSuccess, (state, { cryptos }) => ({
    ...state,
    cryptos,
    error: null,
    loading: false,
  })),
  on(CryptoActions.loadCryptosFailure, (state, { error }) => ({
    ...state,
    error,
  })),
  on(CryptoActions.loadCryptos, (state) => ({
    ...state,
    loading: true,
    error: null,
  })),

  on(CryptoActions.loadCryptosFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error,
  })),

  on(CryptoActions.setSearchTerm, (state, { searchTerm }) => ({
    ...state,
    searchTerm,
  })),

  on(CryptoActions.setPage, (state, { page }) => ({
    ...state,
    currentPage: page,
  }))
);
