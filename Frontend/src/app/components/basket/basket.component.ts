import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";
import {BasketService} from "../../service/basket.service";
import {ProductWithCount} from "../../model/ProductWithCount";

@Component({
  selector: 'app-basket',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './basket.component.html',
  styleUrl: './basket.component.css'
})
export class BasketComponent implements OnInit, OnDestroy {

  idToProduct: Map<number, ProductWithCount> = new Map<number, ProductWithCount>();

  constructor(private basketService: BasketService) {
  }

  ngOnDestroy(): void {
    console.log("Destroyed basket");
  }

  ngOnInit() {
    this.idToProduct = this.basketService.idToProduct;
    console.log("Created basket");
  }

  remove(productId: number) {
    this.idToProduct.delete(productId);
  }

  saveOrder() {
    this.basketService.saveOrder(this.idToProduct);
  }
}
