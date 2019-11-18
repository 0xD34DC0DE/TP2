import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { TraderService } from 'src/app/services/trader.service';

@Component({
  selector: 'app-trader-manage-product',
  templateUrl: './trader-manage-product.component.html',
  styleUrls: ['./trader-manage-product.component.css']
})
export class TraderManageProductComponent implements OnInit {
  pageTitle: string = 'Liste des cochonneries';
  imageWidth: number = 55;
  imageMargin: number = 2;

  products: Product[];

  currentProductToEdit: Product;
  isNewProductModalActive: boolean = false;

  constructor(private traderService: TraderService) {
  }

  ngOnInit(): void {
    this.traderService.getTraderProducts(localStorage.getItem('email')).subscribe((products: Product[]) => this.products = products);
  }

  deleteMethod(product: Product) {
    this.traderService.deleteProduct(localStorage.getItem('email'), product.id).subscribe();
  }

  changeCurrentProductToEdit(product: Product) {
    console.log(product);

    this.currentProductToEdit = product;
  }

  showNewProductModal() {
    this.isNewProductModalActive = true;
  }

}
