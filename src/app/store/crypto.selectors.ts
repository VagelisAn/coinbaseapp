import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CryptoState } from './crypto.reducer';

export const selectCryptoState = createFeatureSelector<CryptoState>('crypto');

export const selectCryptos = createSelector(selectCryptoState, (state) => state.cryptos);
export const selectCryptoError = createSelector(selectCryptoState, (state) => state.error);
