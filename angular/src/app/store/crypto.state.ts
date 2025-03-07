import { createEntityAdapter, EntityState } from '@ngrx/entity';
import { Crypto } from '../models/crypto.model';

export interface CryptoState {
    cryptos: Crypto[];
    loading: boolean;
    error: string | null;
    searchTerm: string;
    sortBy: keyof Crypto | null;
    sortDirection: "asc" | "desc" | null;
    currentPage: number;
    itemsPerPage: number;
  }

  export const cryptoAdapter = createEntityAdapter<Crypto>({
    selectId: (crypto) => crypto.id,
  });
  
  export const initialState: CryptoState = {
    cryptos: [],
    loading: false,
    error: null,
    searchTerm: "",
    sortBy: "name",
    sortDirection: "asc",
    currentPage: 1,
    itemsPerPage: 5,
  };