import { createAction, props } from '@ngrx/store';

export const loadAllCryptos = createAction('[Crypto] Load All Cryptos');
export const loadCryptos = createAction(
  '[Crypto] Load Cryptos',
  props<{ page: number; perPage: number }>()
);
export const loadCryptosSuccess = createAction(
  '[Crypto] Load Cryptos Success',
  props<{ cryptos: any[] }>()
);
export const loadCryptosFailure = createAction(
  '[Crypto] Load Cryptos Failure',
  props<{ error: string }>()
);

export const setSearchTerm = createAction(
  '[Crypto] Set Search Term',
  props<{ searchTerm: string }>()
);

export const setPage = createAction(
  '[Crypto] Set Page',
  props<{ page: number }>()
);
