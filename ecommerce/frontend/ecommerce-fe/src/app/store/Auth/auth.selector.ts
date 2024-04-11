import { createSelector } from '@ngrx/store';
import { AppState } from '../index';

// Selector to get the authentication state from the store
export const selectAuthState = (state: AppState) => state.auth;

// Selector to get the user email from the authentication state
export const selectUserEmail = createSelector(
  selectAuthState,
  (authState) => authState.user?.email // Assuming the user object has an 'email' property
);

// Selector to check if the user is authenticated
export const selectIsAuthenticated = createSelector(
  selectAuthState,
  (authState) => !!authState.user // Assuming the presence of a 'user' object indicates authentication
);
