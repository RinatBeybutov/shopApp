import {Injectable} from '@angular/core';
import {Category} from "../model/Category";
import {Product} from "../model/Product";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/User";

@Injectable({
  providedIn: 'root'
})
export class DataHandlerService {
  constructor(private http: HttpClient) { }

  getCategories() :Observable< Category[] >{
    return this.http.get<Category[]>('http://localhost:80/categories');
  }

  getProducts(): Observable<Product[]>{
    return this.http.get<Product[]>('http://localhost:80/products');
  }

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>('http://localhost:80/users');
  }
}
