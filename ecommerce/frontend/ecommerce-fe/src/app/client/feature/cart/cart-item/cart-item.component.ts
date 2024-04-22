import { Component, Input } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/Model/AppState';
import { CartService } from 'src/app/store/Cart/cart.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.scss']
})
export class CartItemComponent {
  @Input() showButton: any;
  @Input() cartItem: any;
  quantity: number = 0;
  price : number = 0;

  constructor(private cartService: CartService, private store: Store<AppState>) { }
  ngOnInit() {
    console.log(this.cartItem);
    this.quantity = this.cartItem.quantity;
  }
  updateCartItems(num: number) {
    this.updateQuantity(num);
    const requestData = {
      cartItemId: this.cartItem.id,
      data: {
        quantity: num + this.cartItem.quantity,
      },
    };

    console.log(this.cartItem.id);

    this.cartService.updateCartItem(requestData);
  }
  updateQuantity(num: number) {
    this.quantity = this.quantity + num;
  }
  removeCartItem() {
    this.cartService.removeCartItem(this.cartItem.id);
  }
}
