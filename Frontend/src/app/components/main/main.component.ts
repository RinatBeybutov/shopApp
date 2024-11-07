import { Component } from '@angular/core';
import {CategoriesComponent} from "../categories/categories.component";
import {ProductsComponent} from "../products/products.component";

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
export class MainComponent {

}
