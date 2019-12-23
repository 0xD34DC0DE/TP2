import { Component, OnInit } from '@angular/core';
import { Account, AccountInter } from './../../models/account';
import { TraderService } from './../../services/trader.service';
import { Status } from './../../models/status-enum';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-manage-traders',
  templateUrl: './manage-traders.component.html',
  styleUrls: ['./manage-traders.component.css']
})
export class ManageTradersComponent implements OnInit {

  pageTitle: string = 'Liste des traders';
  imageWidth: number = 55;
  imageMargin: number = 2;

  traders: Account[];

  currentTraderToEdit: Account;

  constructor(private traderService: TraderService) { }

  ngOnInit(): void {
    this.traderService.getAllTraders().subscribe((traders: Account[]) => this.traders = traders)

  }

  getTraderList(): Observable<Account[]> {
    return this.traderService.getAllTraders();
  }

  blockTrader(trader: Account) {
    this.traderService.setStatusTrader(trader.email, Status.INACTIVE).subscribe((result: boolean) => {
      this.getTraderList().subscribe((traders: Account[]) => this.traders = traders);

    })
  }

  unblockTrader(trader: Account) {
    this.traderService.setStatusTrader(trader.email, Status.ACTIVE).subscribe((result: boolean) => {
      this.getTraderList().subscribe((traders: Account[]) => this.traders = traders);

    })
  }

  deleteTrader(email: string) {
    this.traderService.deleteTrader(email).subscribe(() => {
      this.getTraderList().subscribe((traders: Account[]) => this.traders = traders);
      console.log("Deleting: " + email);
    });

  }

  changeCurrentTraderToEdit(trader: Account) {
    this.currentTraderToEdit = trader;
  }


}
