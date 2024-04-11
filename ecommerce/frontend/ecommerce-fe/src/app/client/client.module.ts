import { NgModule , CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { HomeComponent } from './feature/home/home.component';
import { AboutComponent } from './feature/about/about.component';
import { ProductsComponent } from './feature/products/products.component';
import { PostComponent } from './feature/post/post.component';
import { ServiceComponent } from './feature/service/service.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';


@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    HomeComponent,
    AboutComponent,
    ProductsComponent,
    PostComponent,
    ServiceComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule
  ],
  exports: [HeaderComponent, FooterComponent]
})
export class ClientModule { }
