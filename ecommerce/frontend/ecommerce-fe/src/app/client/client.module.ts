import { NgModule , CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { HomeComponent } from './feature/home/home.component';
import { AboutComponent } from './feature/about/about.component';
import { ProductsComponent } from './feature/products/products.component';
import { PostComponent } from './feature/post/post.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';
import { CarouselComponent } from './feature/home/carousel/carousel.component';
import { CategoriesComponent } from './feature/home/categories/categories.component';
import { ProductCardComponent } from './shared/product-card/product-card.component';
import { CartComponent } from './feature/cart/cart.component';
import { ErrorComponent } from './feature/error/error.component';
import { ProductDetailComponent } from './feature/product-detail/product-detail.component';
import { OrderComponent } from './feature/order/order.component';
import { CheckoutComponent } from './feature/checkout/checkout.component';
import { OrderDetailComponent } from './feature/order-detail/order-detail.component';
import { ProductSliderComponent } from './feature/home/product-slider/product-slider.component';
import { ServiceComponent } from './feature/home/service/service.component';
import { BrandComponent } from './feature/home/brand/brand.component';
import { ContactComponent } from './feature/contact/contact.component';


@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    HomeComponent,
    AboutComponent,
    ProductsComponent,
    PostComponent,
    FooterComponent,
    HeaderComponent,
    CarouselComponent,
    CategoriesComponent,
    ProductCardComponent,
    CartComponent,
    ErrorComponent,
    ProductDetailComponent,
    OrderComponent,
    CheckoutComponent,
    OrderDetailComponent,
    ProductSliderComponent,
    ServiceComponent,
    BrandComponent,
    ContactComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule
  ],
  exports: [HeaderComponent, FooterComponent]
})
export class ClientModule { }
