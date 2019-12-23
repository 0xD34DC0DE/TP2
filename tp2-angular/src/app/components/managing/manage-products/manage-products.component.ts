import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { TraderService } from 'src/app/services/trader.service';

@Component({
  selector: 'app-manage-products',
  templateUrl: './manage-products.component.html',
  styleUrls: ['./manage-products.component.css']
})
export class ManageProductsComponent implements OnInit {
  pageTitle: string = 'Liste des products';
  imageWidth: number = 55;
  imageMargin: number = 2;

  filteredProducts: Product[];

  products: Product[];

  constructor(private traderService: TraderService) {
  }

  ngOnInit(): void {
    console.log('In OnInit');
    this.traderService.getAllProducts().subscribe((products: Product[]) => {
      this.products = products;
      console.log(this.products);
    })
  }

}