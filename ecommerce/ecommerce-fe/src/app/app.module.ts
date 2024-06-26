import { NgModule , CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import { AuthModule } from './auth/auth.module';
import { ClientModule } from './client/client.module';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { authReducer } from './store/Auth/auth.reducer';
import { productReducer } from './store/Product/product.reducer';
import {cartReducer} from './store/Cart/cart.reducers'

@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    ClientModule,
    FormsModule,
    StoreModule.forRoot(
      {
        auth: authReducer,
        product:productReducer,
        cart:cartReducer
      },
      {}
    ),
   
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
