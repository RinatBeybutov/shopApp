import {Product} from "./Product";

export class ProductWithCount implements Product {
  id: number;
  name: string;
  uuid: string;
  categoryId: number;
  count: number;

  constructor(id: number,
              name: string,
              uuid: string,
              categoryId: number,
              count: number) {
    this.categoryId = categoryId;
    this.id = id;
    this.name = name;
    this.uuid = uuid;
    this.count = count;
  }

  public static getInstance(product: Product): ProductWithCount {
    return new ProductWithCount(product.id, product.name, product.uuid, product.categoryId, 1);
  }

  increaseCount() {
    this.count++;
  }

  decreaseCount() {
    this.count--;
  }
}
