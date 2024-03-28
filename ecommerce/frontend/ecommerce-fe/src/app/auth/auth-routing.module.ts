import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {
    path: 'auth',
    children: [
      {
        path: 'login',
        component: LoginComponent,
        data: { title: 'Login' },
        pathMatch: 'full',
      },
      {
        path: 'register',
        component: RegisterComponent,
        data: { title: 'Register' },
        pathMatch: 'full',
      },
      {
        path: '**',
        redirectTo: '/error',
        pathMatch: 'full',
      }
    ],
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
