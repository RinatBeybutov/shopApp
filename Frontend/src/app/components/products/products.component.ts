import {Component, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";
import {Product} from "../../model/Product";
import {DataHandlerService} from "../../service/data-handler.service";

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

  productsShown: Product[] | undefined;
  productsOriginal: Product[] | undefined;

  constructor(private dataHandler: DataHandlerService) {
  }

  ngOnInit(): void {
    this.dataHandler.getProducts()
      .subscribe(products => this.productsOriginal = this.productsShown = products);

    this.dataHandler.currentCategory.subscribe(categoryId => {
      this.productsShown = this.productsOriginal?.filter(product => product.categoryId === categoryId);
    })
  }

}
