import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { TraderService } from 'src/app/services/trader.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {
  productList: Product[]; 

  constructor(private traderService: TraderService) { }

  ngOnInit() {
    this.traderService.getAllAvailableProducts().subscribe((products: Product[]) => {
      this.productList = products;
    })
  }

}
