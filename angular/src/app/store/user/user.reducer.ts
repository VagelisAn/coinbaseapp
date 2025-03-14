import { createReducer, on } from "@ngrx/store";
import { User } from "../../models/user.model";
import { loadUsers, loadUsersFailure, loadUsersSuccess } from "./user.actions";

export interface UserState {
    users: User[];
    loading: boolean;
    error: string | null;
  }
  
  export const initialState: UserState = {
    users: [],
    loading: false,
    error: null,
  };
  
  export const userReducer = createReducer(
    initialState,
    on(loadUsers, (state) => ({ ...state, loading: true })),
    on(loadUsersSuccess, (state, { users }) => ({ ...state, loading: false, users })),
    on(loadUsersFailure, (state, { error }) => ({ ...state, loading: false, error }))
  );
  