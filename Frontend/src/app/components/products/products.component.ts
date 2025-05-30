import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";
import {Product} from "../../model/Product";
import {BasketService} from "../../service/basket.service";

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  @Input() productsShown: Product[] | undefined;

  constructor(private basketService: BasketService) {
  }

  addToBasket(product: Product) {
    this.basketService.addToBasket(product);
  }
}
