import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { TraderService } from 'src/app/services/trader.service';

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

  products: Product[];

  constructor(private traderService: TraderService) {
    this.filteredProducts = this.products;
    this.listFilter = '';
  }

  performFilter(filterBy: string): Product[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.products.filter((product: Product) =>
      product.name.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

  toggleImage(): void {
    this.showImage = !this.showImage;
  }

  ngOnInit(): void {
    console.log('In OnInit');
    this.traderService.getAllProducts().subscribe((products: Product[]) => {
      this.products = products;
    })
  }

}
