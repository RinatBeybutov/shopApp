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
    return this.http.get<Category[]>('/api/v1/categories');
  }

  getProducts(): Observable<Product[]>{
    return this.http.get<Product[]>('/api/v1/products');
  }

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>('/api/v1/users');
  }
}
