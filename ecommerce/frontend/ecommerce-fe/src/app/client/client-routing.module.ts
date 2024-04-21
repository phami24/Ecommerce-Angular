import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './feature/home/home.component';
import { AboutComponent } from './feature/about/about.component';
import { PostComponent } from './feature/post/post.component';
import { ProductsComponent } from './feature/products/products.component';
import { ErrorComponent } from './feature/error/error.component';
import { CartComponent } from './feature/cart/cart.component';
import { ContactComponent } from './feature/contact/contact.component';
import { ProductDetailComponent } from './feature/product-detail/product-detail.component';
const routes: Routes = [{
  path: 'store',
  children: [
    {
      path: 'home',
      component: HomeComponent,
      pathMatch: 'full'
    },
    {
      path: 'about',
      component: AboutComponent,
      pathMatch: 'full'
    },
    {
      path: 'posts',
      component: PostComponent,
      pathMatch: 'full'
    },
    {
      path: 'products',
      component: ProductsComponent,
      pathMatch: 'full'
    },
    {
      path: 'cart',
      component: CartComponent,
      pathMatch: 'full'
    },
    {
      path: 'contact',
      component: ContactComponent,
      pathMatch: 'full'
    },
    {
      path: 'error',
      component: ErrorComponent,
      pathMatch: 'full'
    },
    {
      path:'product-details/:id',
      component :ProductDetailComponent,
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
