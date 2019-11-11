import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-manage-products',
  templateUrl: './manage-products.component.html',
  styleUrls: ['./manage-products.component.css']
})
export class ManageProductsComponent implements OnInit {
  pageTitle: string = 'Liste des cochonneries';
  imageWidth: number = 55;
  imageMargin: number = 2;
  showImage: boolean = true;

  _listFilter: string;

  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredProducts = this.listFilter ? this.performFilter(this.listFilter) : this.products;
  }

  filteredProducts: Product[];

  products: Product[] = [
    {
      "productId": 545,
      "traderId": "trekoum@mail.com",
      "productName": "bebe",
      "price": 5.99,
      "description": "Le meilleur cafe a vie",
      "imageUrl": "../../../assets/CaféGold.jpg",
      "isRender": false
    }, {
      "productId": 545,
      "traderId": "trekoum@mail.com",
      "productName": "CaféGold",
      "price": 5.99,
      "description": "Le meilleur cafe a vie",
      "imageUrl": "../../../assets/CaféGold.jpg",
      "isRender": false
    }, {
      "productId": 545,
      "traderId": "trekoum@mail.com",
      "productName": "CaféGold",
      "price": 5.99,
      "description": "Le meilleur cafe a vie",
      "imageUrl": "../../../assets/CaféGold.jpg",
      "isRender": false
    }, {
      "productId": 545,
      "traderId": "trekoum@mail.com",
      "productName": "CaféGold",
      "price": 5.99,
      "description": "Le meilleur cafe a vie",
      "imageUrl": "../../../assets/CaféGold.jpg",
      "isRender": false
    }
  ];

  constructor() {
    this.filteredProducts = this.products;
    this.listFilter = '';
  }

  performFilter(filterBy: string): Product[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.products.filter((product: Product) =>
      product.productName.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

  toggleImage(): void {
    this.showImage = !this.showImage;
  }

  ngOnInit(): void {
    console.log('In OnInit');
  }

}
