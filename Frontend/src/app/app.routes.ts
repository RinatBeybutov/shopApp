import { Routes } from '@angular/router';

import {MainComponent} from "./components/main/main.component";
import {BasketComponent} from "./components/basket/basket.component";

export const routes: Routes = [
  {
    path: "",
    component: MainComponent
  },
  {
    path: "basket",
    component: BasketComponent
  },
  {
    path: "**",
    component: MainComponent
  }
];
