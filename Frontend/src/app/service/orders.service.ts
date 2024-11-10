import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Order} from "../model/Order";
import {ProductWithCount} from "../model/ProductWithCount";

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) { }

  getOrders(){
    return this.http.get<Order[]>('http://localhost:80/orders');
  }

  getProductsWithCount(orderId: number) {
    return this.http.get<ProductWithCount[]>('http://localhost:80/products/order/' + orderId);
  }
}
