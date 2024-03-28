import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './feature/home/home.component';
import { AboutComponent } from './feature/about/about.component';

const routes: Routes = [{
  path:'store',
  children:[
    {
      path:'home',
      component:HomeComponent,
      pathMatch:'full'
    },
    {
      path:'about',
      component:AboutComponent,
      pathMatch:'full'
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
