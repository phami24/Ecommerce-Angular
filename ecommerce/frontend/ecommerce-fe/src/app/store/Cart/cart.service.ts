import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { environment } from '../../env/environment';
import { catchError, map, of } from 'rxjs';
import {
    addItemToCartFailure,
    addItemToCartSuccess,
    getCartFailure,
    getCartSuccess,
    removeCartItemFailure,
    removeCartItemSuccess,
    updateCartItemFailure,
    updateCartItemSuccess,
} from './cart.actions';
import { ApiService } from '../../client/service/api.service';
import { AppState } from 'src/app/Model/AppState';

@Injectable({
    providedIn: 'root',
})
export class CartService {
    BASE_API_URL = environment.apiUrl;

    constructor(
        private store: Store<AppState>,
        private http: HttpClient,
        private apiService: ApiService
    ) { }

    addItemToCart(requestData: any) {
        const url = `${this.BASE_API_URL}/api/cart/add`;

        return this.http
            .put(url, requestData, { headers: this.apiService.getHeader() })
            .pipe(
                map((data: any) => {
                    console.log(data);
                    return addItemToCartSuccess({ payload: data });
                }),
                catchError((error: any) => {
                    console.log(error)
                    return of(addItemToCartFailure(this.apiService.handleError(error)));
                })
            )
            .subscribe((action) => this.store.dispatch(action));
    }

    getCart() {
        const url = `${this.BASE_API_URL}/api/cart`;

        return this.http
            .get(url, { headers: this.apiService.getHeader() })
            .pipe(
                map((data: any) => {
                    return getCartSuccess({ payload: data });
                }),
                catchError((error: any) => {
                    return of(getCartFailure(this.apiService.handleError(error)));
                })
            )
            .subscribe((action) => this.store.dispatch(action));
    }

    removeCartItem(cartItemId: number) {
        const url = `${this.BASE_API_URL}/api/cartItem/${cartItemId}`;

        return this.http
            .delete(url, { headers: this.apiService.getHeader() })
            .pipe(
                map((data: any) => {
                    console.log(data);

                    return removeCartItemSuccess({ cartItemId });
                }),
                catchError((error: any) => {
                    return of(removeCartItemFailure(this.apiService.handleError(error)));
                })
            )
            .subscribe((action) => this.store.dispatch(action));
    }

    updateCartItem(requestData: any) {
        console.log(requestData);

        const url = `${this.BASE_API_URL}/api/cartItem/${requestData.cartItemId}`;

        return this.http
            .put(url, requestData.data, { headers: this.apiService.getHeader() })
            .pipe(
                map((data: any) => {
                    console.log(data);
                    return updateCartItemSuccess({ payload: data });
                }),
                catchError((error: any) => {
                    return of(updateCartItemFailure(this.apiService.handleError(error)));
                })
            )
            .subscribe((action) => this.store.dispatch(action));
    }
    countItem : number = 0;
    getCartItemCount() {
        this.getCart();
        this.store.pipe(select((store) => store.cart)).subscribe((cart) => {
          this.countItem = cart.cartItems.size();
          console.log(this.countItem);
        });
    }
}