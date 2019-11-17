import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { TraderService } from 'src/app/services/trader.service';
import { identifierModuleUrl } from '@angular/compiler';

@Component({
  selector: 'app-trader-manage-product',
  templateUrl: './trader-manage-product.component.html',
  styleUrls: ['./trader-manage-product.component.css']
})
export class TraderManageProductComponent implements OnInit {
  pageTitle: string = 'Liste des cochonneries';
  imageWidth: number = 55;
  imageMargin: number = 2;

  filteredProducts: Product[];
  //products: Product[];
  products: Product[] = [
    {
      'id':5.99,
      'traderId':'4855',
    'name':'fish',
    'price':5.99,
    'availableStock':'150',
    'available':true
  },{
    'id':5.59,
    'traderId':'7899',
    'name':'fish',
    'price':5.99,
    'availableStock':'120',
    'available':true
  }
];
currentProductToEdit: Product = this.products[0];

constructor(private traderService: TraderService) {
  }

  ngOnInit(): void {
   /* console.log('In OnInit');
    this.traderService.getAllProducts().subscribe((products: Product[]) => {
      this.products = products;
      console.log(this.products);
    })*/
  }

  deleteMethod(){

  }

  updateMethod(){

  }

  changeCurrentProductToEdit(product:Product){
    console.log(product);
    
    this.currentProductToEdit = product;
  }

}
