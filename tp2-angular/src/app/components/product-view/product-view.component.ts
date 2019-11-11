import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {
  productList: Product[] = [
    {
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
    }, {
      "productId": 545,
      "traderId": "trekoum@mail.com",
      "productName": "CaféGold",
      "price": 5.99,
      "description": "Le meilleur cafe a vie",
      "imageUrl": "../../../assets/CaféGold.jpg",
      "isRender": false
    }

  ]
  constructor() { }

  ngOnInit() {
  }

}
