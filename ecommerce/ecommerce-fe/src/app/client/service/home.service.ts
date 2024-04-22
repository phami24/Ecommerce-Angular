import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../env/environment';
import { catchError, map, of } from 'rxjs';
import { ApiService } from '../../client/service/api.service';

@Injectable({
    providedIn: 'root',
})
export class HomeService {
    BASE_API_URL = environment.apiUrl;

    constructor(
        private http: HttpClient,
        private apiService: ApiService
    ) { }

    getFeatureProduct() {
        return this.http
            .get(`${this.BASE_API_URL}/home/get-random`).pipe()
    }
    getNewProduct(){
        return this.http.get(`${this.BASE_API_URL}/home/get-new`).pipe()
    }
}