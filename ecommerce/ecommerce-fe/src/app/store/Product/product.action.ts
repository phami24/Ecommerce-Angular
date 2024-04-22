import { createAction, props } from '@ngrx/store';
export const findProductByFilterRequest = createAction(
    '[Product] Find Product By Filter Request'
  );
  
  export const findProductByFilterSuccess = createAction(
    '[Product] Find Product By Filter Success',
    props<{ payload: any }>()
  );
  
  export const findProductByFilterFailure = createAction(
    '[Product] Find Product By Filter Failure',
    props<{ error: any }>()
  );
  // FindProductByFilter
  
  // Find Product By Id
  export const findProductByIdRequest = createAction(
    '[Product] Find Product By Id Request'
  );
  
  export const findProductByIdSuccess = createAction(
    '[Product] Find Product By Id Success',
    props<{ payload: any }>()
  );
  
  export const findProductByIdFailure = createAction(
    '[Product] Find Product By Id Failure',
    props<{ error: any }>()
  );
  // Find Product By Id