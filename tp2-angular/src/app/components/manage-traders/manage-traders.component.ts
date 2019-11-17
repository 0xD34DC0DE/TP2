import { Component, OnInit } from '@angular/core';
import { Traders, AccountInter } from 'src/app/models/account';
import { TraderService } from 'src/app/services/trader.service';
import { Status } from 'src/app/models/status-enum';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-manage-traders',
  templateUrl: './manage-traders.component.html',
  styleUrls: ['./manage-traders.component.css']
})
export class ManageTradersComponent implements OnInit {

  pageTitle: string = 'Liste des cochonneries';
  imageWidth: number = 55;
  imageMargin: number = 2;

  traders: AccountInter[] = [
    {
      'email': 'quick@mail.com',
      'firstName':'emerick',
      'lastName':'benlie',
      'address':'67 emly street',
      'phone':'514-366-8978',
      'type': 'TRADER',
      'roles':['client'],
      'status':Status.ACTIVE
  }
];

currentTraderToEdit: AccountInter = this.traders[0];

  constructor(private traderService: TraderService) { }

  ngOnInit(): void {
    console.log('In OnInit');
 //   this.getProductList().subscribe((traders: Traders[]) => this.traders = traders);
    console.log(this.traders);
  }

  getProductList() : Observable<Traders[]> {
    return this.traderService.getAllTraders();
  }

  blockTrader(trader: Traders) {
    this.traderService.setStatusTrader(trader.email, Status.INACTIVE).subscribe((result: boolean) => {
      this.getProductList().subscribe((traders: Traders[]) => this.traders = traders);
      console.log(result);
    })
  }

  unblockTrader(trader: Traders) {
    this.traderService.setStatusTrader(trader.email, Status.ACTIVE).subscribe((result: boolean) => {
      this.getProductList().subscribe((traders: Traders[]) => this.traders = traders);
      console.log(result);
    })
  }

  deleteTrader(email: string) {
    this.traderService.deleteTrader(email).subscribe(() => {
      this.getProductList().subscribe((traders: Traders[]) => this.traders = traders);
      console.log("Deleting" + email);
    });
   
  }

  changeCurrentTraderToEdit(trader:AccountInter){
    console.log(trader);
    
    this.currentTraderToEdit = trader;
  }

  
}
