export class Product {
  id: number
  uuid: string
  name: string
  categoryId: number

  constructor(id: number, uuid: string, name: string, categoryId: number) {
    this.id = id;
    this.uuid = uuid;
    this.name = name;
    this.categoryId = categoryId;
  }
}
