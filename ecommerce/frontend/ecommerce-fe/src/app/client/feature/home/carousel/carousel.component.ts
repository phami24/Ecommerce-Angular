import { Component } from '@angular/core';
import { carouselData } from './data';
@Component({
  selector: 'app-home-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent {
  carouselData: any;

  currentSlide = 0;
  interval: any;

  ngOnInit() {
    this.carouselData = carouselData;
    this.autoPlay();
  }

  autoPlay() {
    setInterval(() => {
      this.nextSlide();
    }, 4000);
  }

  nextSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.carouselData.length;
  }
}
