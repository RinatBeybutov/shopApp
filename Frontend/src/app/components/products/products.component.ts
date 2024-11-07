import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";
import {Product} from "../../model/Product";

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
}
