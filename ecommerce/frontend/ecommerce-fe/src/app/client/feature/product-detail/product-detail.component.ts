import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store, select } from '@ngrx/store';
import { AppState } from 'src/app/Model/AppState';
import { CartService } from 'src/app/store/Cart/cart.service';
import { ProductService } from 'src/app/store/Product/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent {
  productDetails: any;
  constructor(
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private store: Store<AppState>,
    private cartService:CartService
  ) { }
  ngOnInit() {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    console.log(id);
    this.productService.findProductsById(id);
    this.store.pipe(select((store) => store.product)).subscribe((product) => {
      this.productDetails = product.product;
      console.log(this.productDetails);
    });
  }
  getStars(num: number): any[] {
    return new Array(num);
  }

  handleAddToCart(id : number) {
    const data = { productId: id };
    this.cartService.addItemToCart(data);
    this.cartService.getCart();
  }
}
