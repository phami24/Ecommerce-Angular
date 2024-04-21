import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { environment } from '../../env/environment';
import {
  findProductByFilterFailure,
  findProductByFilterSuccess,
  findProductByIdFailure,
  findProductByIdSuccess,
} from './product.action';
import { catchError, map, of } from 'rxjs';
import { ApiService } from '../../client/service/api.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  BASE_API_URL = environment.apiUrl;

  constructor(
    private store: Store,
    private http: HttpClient,
    private apiService: ApiService
  ) { }

  findProductsByCategory(requestData: any) {
    const {
      colors,
      minPrice,
      maxPrice,
      category,
      sort,
      pageNumber,
      pageSize,
    } = requestData;

    let params = new HttpParams()
      .set('color', colors)
      .set('minPrice', minPrice)
      .set('maxPrice', maxPrice)
      .set('category', category)
      .set('sort', sort)
      .set('pageNumber', pageNumber)
      .set('pageSize', pageSize);

    return this.http
      .get(`${this.BASE_API_URL}/api/product/filter`, {
        headers: this.apiService.getHeader(),
        params,
      })
      .pipe(
        map((data: any) => {
          console.log('products', data);
          return findProductByFilterSuccess({ payload: data });
        }),
        catchError((error: any) => {
          console.log(error)
          return of(
            findProductByFilterFailure(this.apiService.handleError(error))
          );
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }

  findProductsById(productId: any) {
    return this.http
      .get(`${this.BASE_API_URL}/api/product/${productId}`, {
        headers: this.apiService.getHeader(),
      })
      .pipe(
        map((data: any) => {
          console.log('productId', data);
          return findProductByIdSuccess({ payload: data });
        }),
        catchError((error: any) => {
          return of(findProductByIdFailure(this.apiService.handleError(error)));
        })
      )
      .subscribe((action) => this.store.dispatch(action));
  }
}