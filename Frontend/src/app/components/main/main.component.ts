import { Component, OnInit } from '@angular/core';
import {CategoriesComponent} from "../categories/categories.component";
import {ProductsComponent} from "../products/products.component";
import { DataHandlerService } from '../../service/data-handler.service';
import { forkJoin } from 'rxjs';
import { Category } from '../../model/Category';
import { Product } from '../../model/Product';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    CategoriesComponent,
    ProductsComponent,
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {
  categories: Category[] | undefined;
  currentCategoryId: number | undefined;
  products: Product[] | undefined;
  productsShown: Product[] | undefined;

  constructor(private dataHandler: DataHandlerService) {}

  ngOnInit() {
    forkJoin([this.dataHandler.getCategories(), this.dataHandler.getProducts()]).subscribe(([categories, products]: [Category[],  Product[]]) => {
      this.categories = categories;
      this.products = this.productsShown = products;
      this.changeCategory(categories[0].id);
    });
  }

  changeCategory(categoryId: number): void {
    this.currentCategoryId = categoryId;

    this.productsShown = this.products?.filter((product) => product.categoryId === categoryId);
  }
}
