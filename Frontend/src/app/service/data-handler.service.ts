import { Injectable } from '@angular/core';
import {Category} from "../model/Category";
import {TestData} from "../data/TestData";
import {Product} from "../model/Product";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataHandlerService {

  constructor(private http: HttpClient) { }

  data: Category[] = [];

  getCategories() : Category[] {
    this.http.get<Category[]>('http://localhost:80/categories')
      .subscribe(res => {
      this.data = res;
    })
    return this.data;
  }

  getProducts() : Product[] {
    return TestData.products;
  }

  getProductsByCategory(categoryId: number): Product[] {
    return TestData.products.filter(product => product.categoryId === categoryId);
  }


}
