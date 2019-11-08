import { Component, OnInit } from '@angular/core';
import { IProduct } from './product';

@Component({
  selector: 'pm-products',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  
  pageTitle: string = 'Liste des cochonneries';
  imageWidth: number = 55;
  imageMargin: number = 2;
  showImage: boolean = false;

  _listFilter: string;

  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredProducts = this.listFilter ? this.performFilter(this.listFilter) : this.products;
  }

  filteredProducts: IProduct[];

  products: IProduct[] = [
    {
      "productId": 1,
      "productName": "Poutine-D'Onofrio",
      "productCode": "COCHONNERIE-123",
      "releaseDate": "01 novembre 2019",
      "description": "Poutine de qualité royale",
      "price": 758.99,
      "starRating": 4.2,
      "imageUrl": "assets/images/poutine.jpg"
    },
    {
      "productId": 5,
      "productName": "Hot dog Fabory",
      "productCode": "COCHONNERIE-456",
      "releaseDate": "01 novembre 2010",
      "description": "Hot dog fait avec du vrai chien de Fabory",
      "price": 120,
      "starRating": 4.8,
      "imageUrl": "assets/images/hotdog.jpg"
    },
    {
      "productId": 5,
      "productName": "Pepsi Simon",
      "productCode": "COCHONNERIE-789",
      "releaseDate": "01 novembre 2015",
      "description": "Pepsi aux gaz de Simon",
      "price": 500,
      "starRating": 4.8,
      "imageUrl": "assets/images/perrier.jpg"
    }
  ];

  constructor() {
    this.filteredProducts = this.products;
    this.listFilter = '';
  }

  performFilter(filterBy: string): IProduct[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.products.filter((product: IProduct) =>
      product.productName.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

  toggleImage(): void {
    this.showImage = !this.showImage;
  }

  ngOnInit(): void {
    console.log('In OnInit');
  }
}
