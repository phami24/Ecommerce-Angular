import { categoriesData } from './categories.data';
import { Component } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent {
  categoriesData: any
  ngOnInit() {
    {
      this.categoriesData = categoriesData;
    }
  }
}
