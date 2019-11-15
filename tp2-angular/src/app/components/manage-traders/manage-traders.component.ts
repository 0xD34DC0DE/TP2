import { Component, OnInit } from '@angular/core';
import { Traders } from 'src/app/models/account';
import { TraderService } from 'src/app/services/trader.service';
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

  traders: Traders[];

  constructor(private traderService: TraderService) {}

  ngOnInit(): void {
    console.log('In OnInit');
    this.traderService.getAllTraders().subscribe((traders: Traders[]) => { 
      this.traders = traders;
      console.log(this.traders);
    });
  }

  blockTrader(trader: Traders) {
    trader.status = Status.INACTIVE;
    this.traderService.updateTrader(trader).subscribe((trader: Traders) => {
      console.log(trader);
    })
  }

  unblockTrader(trader: Traders) {
    trader.status = Status.ACTIVE;
    this.traderService.updateTrader(trader).subscribe((trader: Traders) => {
      console.log(trader);
    })
  }

  deleteTrader(email: string) {
    this.traderService.deleteTrader(email).subscribe();
  }

}
