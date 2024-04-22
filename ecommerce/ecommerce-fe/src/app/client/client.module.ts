import { FormsModule,FormBuilder,ReactiveFormsModule  } from '@angular/forms';
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
import {MatSelectModule} from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSliderModule} from '@angular/material/slider';
import {MatChipsModule} from '@angular/material/chips';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ProductReviewRatingComponent } from './feature/product-detail/product-review-rating/product-review-rating.component';
import {MatBadgeModule} from '@angular/material/badge';
import { CartItemComponent } from './feature/cart/cart-item/cart-item.component';
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
    ProductReviewRatingComponent,
    CartItemComponent,
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatMenuModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatRadioModule,
    MatProgressBarModule,
    MatSliderModule,
    MatChipsModule,
    MatPaginatorModule,
    MatBadgeModule
  ],
  exports: [HeaderComponent, FooterComponent]
})
export class ClientModule { }
