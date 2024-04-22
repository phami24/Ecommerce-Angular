import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private router : Router){}
  getHeader(): HttpHeaders {
    const token = localStorage.getItem('userToken');
  
    if (token) {
      return new HttpHeaders().set('Authorization', `Bearer ${token}`);
    } else {
      console.error('No JWT token found in localStorage');
      alert('You are not logged in. Please log in to continue.');
      this.router.navigate(['/login']);
      return new HttpHeaders();
    }
  }
  

  handleError(error: any): any {
    return of(
      error.response && error.response.data.message
        ? error.response.data.message
        : error.message
    );
  }
}
