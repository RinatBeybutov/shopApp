import { Component } from '@angular/core';
import {CategoriesComponent} from "../categories/categories.component";
import {ProductsComponent} from "../products/products.component";
import {HeaderComponent} from "../header/header.component";

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    CategoriesComponent,
    ProductsComponent,
    HeaderComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
