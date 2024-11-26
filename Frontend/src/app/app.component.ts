import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {CategoriesComponent} from "./components/categories/categories.component";
import {ProductsComponent} from "./components/products/products.component";
import { HeaderComponent } from "./components/header/header.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CategoriesComponent, ProductsComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent { 
}
