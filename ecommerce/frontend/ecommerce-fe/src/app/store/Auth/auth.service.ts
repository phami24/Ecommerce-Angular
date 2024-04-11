import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { environment } from 'src/app/env/environment';
import { map, catchError, of, Observable } from 'rxjs';
import {
    loginFailure,
    loginSuccess,
    logout,
    registerFailure,
    registerSuccess,
} from './auth.action';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApiService } from '../../client/service/api.service';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private baseUrlAPI = environment.apiUrl + '/auth';

    constructor(
        private http: HttpClient,
        private store: Store,
        private apiService: ApiService,
        private router: Router
    ) { }

    login(email: string, password: string): Observable<any> { // Ensure the return type is Observable<any>
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        const body = { email, password };
        console.log(this.baseUrlAPI);
        return this.http
            .post(`${this.baseUrlAPI}/signin`, body, { headers })
            .pipe(
                map((user: any) => {
                    console.table('Login user', user);
                    if (user.token) {
                        localStorage.setItem('userToken', user.token);
                    }
                    return loginSuccess({ user });
                }),
                catchError((error) => {
                    const errorMsg = this.apiService.handleError(error);
                    return of(loginFailure(errorMsg));
                })
            );
    }

    register(user: any): Observable<any> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http
            .post(`${this.baseUrlAPI}/signup`, user, { headers })
            .pipe(
                map((user: any) => {
                    console.log('register user', user);
                    return registerSuccess({ user });
                }),
                catchError((error) => {
                    const errorMsg = this.apiService.handleError(error);
                    return of(registerFailure(errorMsg));
                })
            )
    }

    logout(): void {
        // Xóa thông tin đăng nhập từ localStorage hoặc bất kỳ cơ sở dữ liệu cục bộ nào
        localStorage.removeItem('userToken'); 
        this.store.dispatch(logout());
        // Chuyển hướng người dùng đến trang đăng nhập hoặc trang chính của ứng dụng
        this.router.navigate(['/auth/login']); // Chuyển hướng đến trang đăng nhập
    }
}