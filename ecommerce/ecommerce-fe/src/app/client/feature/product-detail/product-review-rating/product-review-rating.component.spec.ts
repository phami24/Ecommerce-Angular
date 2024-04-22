import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductReviewRatingComponent } from './product-review-rating.component';

describe('ProductReviewRatingComponent', () => {
  let component: ProductReviewRatingComponent;
  let fixture: ComponentFixture<ProductReviewRatingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductReviewRatingComponent]
    });
    fixture = TestBed.createComponent(ProductReviewRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
