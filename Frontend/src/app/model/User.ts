export class User {
  name: string;
  email: string;
  date: Date;

  constructor(name: string, email: string, date: Date) {
    this.name = name;
    this.email = email;
    this.date = date;
  }
}
