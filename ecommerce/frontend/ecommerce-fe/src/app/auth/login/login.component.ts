import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/store/Auth/auth.service';
import { loginSuccess } from 'src/app/store/Auth/auth.action';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  hidePassword = true;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private store: Store,
  ) {
    this.loginForm = this.fb.group({
      email: '',
      password: '',
    });
  }

  ngOnInit() {
    // Nếu đã đăng nhập, tự động chuyển hướng đến trang home
    if (this.isLoggedIn()) {
      this.router.navigate(['/store/home']); // Chuyển hướng đến trang home
    } else {

      this.loginForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required]],
      });
    }
  }

  submit() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value);
      this.authService.login(this.loginForm.value.email, this.loginForm.value.password).subscribe(
        (action) => {
          if (action.type === loginSuccess.type) {
            this.onLoginSuccess(); // Pass user data to onLoginSuccess
          } else {
            this.loginForm.setErrors({ invalidCredentials: true });
          }
        },
        (error) => {
          console.error("Login failed:", error);
          this.loginForm.setErrors({ serverError: true });
        }
      );

    }
  }
  onLoginSuccess() {
    alert("Login Succes!")
    this.router.navigate(['/store/home']);
  }
  showPassword: boolean = false;

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
  isLoggedIn(): boolean {
    const token = localStorage.getItem("userToken");
    if (token) {
      return true;
    }
    return false; // Giả sử mặc định là chưa đăng nhập
  }
}

