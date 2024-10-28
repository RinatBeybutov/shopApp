import {Component, OnInit} from '@angular/core';
import {DataHandlerService} from "../../service/data-handler.service";
import {Category} from "../../model/Category";
import {NgForOf} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [
    NgForOf,
    HttpClientModule
  ],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.css'
})
export class CategoriesComponent implements OnInit {

  categories: Category[] | undefined;

  constructor(private dataHandler: DataHandlerService,
              private router: Router ) {
  }

  ngOnInit() {
    this.dataHandler.getCategories()
      .subscribe(categories =>  {
        this.categories = categories;
        this.dataHandler.currentCategory.next(categories[0].id);
      });

    this.router.events.subscribe(event => {
      console.log(event);
    })
  }

  changeCurrentCategory(id: number) {
    this.dataHandler.currentCategory.next(id);
  }
}
