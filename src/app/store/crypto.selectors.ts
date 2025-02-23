import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CryptoState } from './crypto.state';


export const selectCryptoState = createFeatureSelector<CryptoState>('crypto');

export const selectCryptos = createSelector(selectCryptoState, (state) => state.cryptos);
export const selectCryptoError = createSelector(selectCryptoState, (state) => state.error);

export const selectCryptoLoading = createSelector(selectCryptoState, (state) => state.loading);

export const selectTotalCryptos = createSelector(selectCryptos, cryptos => cryptos.length);
export const selectSearchTerm = createSelector(selectCryptoState, (state) => state.searchTerm);
export const selectCurrentPage = createSelector(selectCryptoState, (state) => state.currentPage);
export const selectItemsPerPage = createSelector(selectCryptoState, (state) => state.itemsPerPage);

export const selectFilteredCryptos = createSelector(
  selectCryptos,
  selectSearchTerm,
  selectCurrentPage,
  selectItemsPerPage,
  (cryptos, searchTerm, currentPage, itemsPerPage) => {
    let filteredCryptos = cryptos;

    if (searchTerm) {
      filteredCryptos = filteredCryptos.filter((crypto) =>
        crypto.name.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }
    console.log(filteredCryptos)
    const startIndex = (currentPage - 1) * itemsPerPage;
    console.log(startIndex, currentPage, itemsPerPage ,filteredCryptos.slice(startIndex, startIndex + itemsPerPage))
    return filteredCryptos.slice(startIndex, startIndex + itemsPerPage);
  }
);
