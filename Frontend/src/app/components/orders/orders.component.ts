import {Component, OnInit} from '@angular/core';
import {OrdersService} from "../../service/orders.service";
import {Order} from "../../model/Order";
import {NgForOf} from "@angular/common";
import {ProductWithCount} from "../../model/ProductWithCount";

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {
  orders: Order[] | undefined;
  orderIdToProductWithCount: Map<number, ProductWithCount[]> | undefined;

  constructor(private ordersService: OrdersService) {
  }

  ngOnInit(): void {
    this.ordersService.getOrders()
      .subscribe(orders => {
        this.orders = orders;
      });
  }

  requestProducts(orderId: number) {
    if (this.orderIdToProductWithCount?.has(orderId)) {
      return;
    }

    this.ordersService.getProductsWithCount(orderId)
      .subscribe(products =>{
        this.orderIdToProductWithCount?.set(orderId, products);
      })
  }

  getProducts(id: number) {
    return this.orderIdToProductWithCount?.get(id);
  }
}
