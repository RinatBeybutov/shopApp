import {Injectable} from '@angular/core';
import {Product} from "../model/Product";
import {ProductWithCount} from "../model/ProductWithCount";
import {ProductDto} from "../model/ProductDto";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BasketService {

  // ProductId : ProductWithCount
  idToProduct: Map<number, ProductWithCount> = new Map<number, ProductWithCount>();

  constructor(private http: HttpClient) { }

  addToBasket(product: Product) {

    console.log(product);

    // TODO: Узнать, почему здесь все равно ошибка может быть?
    if(this.idToProduct.has(product.id)) {
      // @ts-ignore
      this.idToProduct.get(product.id).increaseCount();
    } else {
      this.idToProduct.set(product.id, ProductWithCount.getInstance(product));
    }
  }

  saveOrder(productToCount: Map<number, ProductWithCount>) {
    let products: ProductDto[] = [];
    productToCount.forEach((value, key) => {
      products.push(new ProductDto(key, value.count));
    });

    // TODO: Добавить сюда uuid текущего пользователя
    let body = {
      "createdAt" : this.formatDate(new Date()),
      "userUuid" : "423bd97c-f1af-413c-9f62-18b4ab158293",
      "products" : products
    }

    this.http.post("/api/v1/orders", body).subscribe();
  }

  formatDate(date: Date): string {
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();
    return `${year}-${month}-${day}`;
  }

}
