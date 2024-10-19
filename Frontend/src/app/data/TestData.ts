import {Category} from "../model/Category";
import {Product} from "../model/Product";

export class TestData {

  static categories: Category[] = [
    {id: 1, uuid: "", name: 'Пицца'},
    {id: 2, uuid: "", name: 'Напитки'},
    {id: 3, uuid: "", name: "Сеты"}
  ]

  static products: Product[] = [
    {id: 1, name: "Пицца", categoryId: 1},
    {id: 2, name: "кока-кола", categoryId: 2},
    {id: 3, name: "Сет королевский", categoryId: 3}
  ]

}
