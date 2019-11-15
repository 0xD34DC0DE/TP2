import { Component, OnInit } from '@angular/core';
import { Traders } from 'src/app/models/account';
import { Status } from 'src/app/models/status-enum';

@Component({
  selector: 'app-manage-traders',
  templateUrl: './manage-traders.component.html',
  styleUrls: ['./manage-traders.component.css']
})
export class ManageTradersComponent implements OnInit {

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
    this.filteredTraders = this.listFilter ? this.performFilter(this.listFilter) : this.traders;
  }

  filteredTraders: Traders[];


  traders: Traders[] = [
    {
      "email": "toto@mail.com",
      "firstName": "toto",
      "lastName": "toto",
      "address": "toto adresse 6773",
      "phone": "5147889878",
      "type": "TRADER",
      "status": Status.ACTIF,
      "cart": null,
      "roles": [],
      "balance": 5.99
    }
  ];
  constructor() {
    this.filteredTraders = this.traders;
    this.listFilter = '';
  }

  performFilter(filterBy: string): Traders[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.traders.filter((trader: Traders) =>
      trader.email.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

  toggleImage(): void {
    this.showImage = !this.showImage;
  }

  ngOnInit(): void {
    console.log('In OnInit');
    console.log(this.traders);
    
  }

}
