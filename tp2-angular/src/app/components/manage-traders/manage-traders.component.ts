import { Component, OnInit } from '@angular/core';
import { Traders } from 'src/app/models/account';
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

  traders: Traders[];

  constructor(private traderService: TraderService) { }

  ngOnInit(): void {
    console.log('In OnInit');
    this.getProductList().subscribe((traders: Traders[]) => this.traders = traders);
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

}
