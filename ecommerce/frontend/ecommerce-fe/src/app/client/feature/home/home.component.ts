import { map } from 'rxjs';
import { Component } from '@angular/core';
import { menJackets } from './men';
import { HomeService } from '../../service/home.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  menJackets: any;
  randomProducts: any;
  newProducts : any;
  constructor(
    private homeService: HomeService,
  ) { }
  ngOnInit() {
    this.menJackets = menJackets;
    this.getRandomProduct();
    this. getNewProduct();
  }
  getRandomProduct() {
    this.homeService.getFeatureProduct().subscribe((res: any) => {
      this.randomProducts = res;
      console.log(this.randomProducts);
    })
  }

  getNewProduct(){
    this.homeService.getNewProduct().subscribe((res : any) =>{
      this.newProducts = res;
      console.log(this.newProducts);
    })
  }
}