import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { registerSuccess } from 'src/app/store/Auth/auth.action';
import { AuthService } from 'src/app/store/Auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {

  registrationForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private store: Store,
    private router: Router
  ) {
    this.registrationForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), this.passwordValidator],],
      mobile: ['', [Validators.required, Validators.minLength(6), this.phoneValidator],],
    });
  }
  ngOnInit(): void {
  }

  // Custom validator for password
  passwordValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const passwordPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
    if (!passwordPattern.test(control.value)) {
      return { invalidPassword: true };
    }
    return null;
  }

  phoneValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const phonePattern = /^[0-9]*$/;
    if (!phonePattern.test(control.value)) {
      return { invalidPhone: true };
    }
    return null;
  }
  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }

  onSubmit() {
    if (this.registrationForm.valid) {
      const user = this.registrationForm.value;
      console.log(user);
      this.authService.register(user).subscribe(
        (action) => {
          if (action.type === registerSuccess.type) {
            console.table(user);
            alert("Register Success!")
            this.router.navigate(['/auth/login']);
          } else {
            this.registrationForm.setErrors({ invalidCredentials: true });
          }
        },
        (error) => {
          console.error("Login failed:", error);
          this.registrationForm.setErrors({ serverError: true });
        }
      );
    }
  }

  showPassword: boolean = false;

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

}


