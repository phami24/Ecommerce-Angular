
import { categoriesData } from './../home/categories/categories.data';
import { ProductService } from './../../../store/Product/product.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatChipListboxChange, MatChipSelectionChange } from '@angular/material/chips';
import { Store, select } from '@ngrx/store';
import { AppState } from '../../../Model/AppState'
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {

  products: any;
  pageNumber: number = 0;
  pageSize: number = 4;
  desktopFilterForm: FormGroup;
  priceSelectedValue?: string = '1-10000';
  sortSelectedValue?: string = 'price_low';
  colors: string[] = [];
  category: string = "";
  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private store: Store<AppState>
  ) {
    this.desktopFilterForm = this.formBuilder.group({
      colors: this.formBuilder.group({
        White: false,
        Black: false,
        Red: false
      }),
      categories: this.formBuilder.group({
        Mouse: false,
        Keyboard: false,

      })
    });
  }
  ngOnInit() {
    this.desktopFilterForm = this.formBuilder.group({
      colors: this.formBuilder.group({
        White: false,
        Black: false,
        Red: false
      }),
      categories: this.formBuilder.group({
        mouse: false,
        keyboard: false
      })
    });
    this.getProductWithFilter(this.pageNumber);
  }
  isOpenMobilFilter: boolean = false;
  isSortSelectionOpen: boolean = false;
  openMobilFilter() {
    this.isOpenMobilFilter = true;
  }
  closeMobilFilter() {
    this.isOpenMobilFilter = false;
  }
  toggelSortButton() {
    this.isSortSelectionOpen = !this.isSortSelectionOpen;
  }
  formatLabel(value: number): string {
    if (value >= 1) {
      return '$' + Math.round(value);
    }

    return `${value}`;
  }

  onSelectionChange(event: MatChipListboxChange): void {
    this.priceSelectedValue = event.source.value;
    console.log(this.priceSelectedValue);
  }

  handleSortSelection(sortOption: string): void {
    this.sortSelectedValue = sortOption;
    console.log('Selected Sort Option:', this.sortSelectedValue);
  }

  onSubmit() {
    this.getProductWithFilter(this.pageNumber);
  }

  getProductWithFilter(pageNumber: number) {
    this.colors = [];
    this.category = "";
    const formData = this.desktopFilterForm.value;
    console.log(formData.colors);
    for (const color in formData.colors) {
      if (formData.colors[color]) {
        this.colors.push(color);
      }
    }
    for (const category in formData.categories) {
      if (formData.categories[category]) {
        console.log(formData.categories[category]);
        this.category = formData.categories[category];
      }
    }
    console.log(this.colors);
    console.log(this.category);
    var requestParams = {
      colors: this.colors,
      minPrice: this.priceSelectedValue?.split('-')[0],
      maxPrice: this.priceSelectedValue?.split('-')[1],
      category: this.category,
      sort: this.sortSelectedValue,
      pageNumber: this.pageNumber,
      pageSize: this.pageSize,
    }
    console.log('Request Params:', requestParams);
    this.productService.findProductsByCategory(requestParams);

    this.store.pipe(select((store) => store.product)).subscribe((product) => {
      this.products = product.products;
    });
  }

  onPageChanged(event: PageEvent): void {
    this.pageNumber = event.pageIndex;
    this.getProductWithFilter(this.pageNumber);
  }
  resetFilter(): void {
    this.desktopFilterForm.reset({
      colors: {
        White: false,
        Black: false,
        Red: false
      },
      categories: {
        mouse: false,
        keyboard: false
      }
    });

    this.priceSelectedValue = '1-10000';
    this.sortSelectedValue = 'price_low';
    this.pageNumber = 0;
    this.getProductWithFilter(this.pageNumber);
  }
}