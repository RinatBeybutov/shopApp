import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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

  @Input() categories: Category[] | undefined;
  @Input() currentCategoryId: number | undefined;
  @Output() onCategoryChange = new EventEmitter<number>();

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe(event => {
      console.log(event);
    })
  }

  changeCurrentCategory(id: number): void {
    this.onCategoryChange.emit(id);
  }
}
