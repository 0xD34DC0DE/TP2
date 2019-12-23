import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { Account } from 'src/app/models/account';
import { TraderService } from 'src/app/services/trader.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  accountToEdit: Account;
  account: Account;

  isEditorActive: boolean = false;

  constructor(private authService: AuthenticationService, private traderService: TraderService, private router: Router) { }

  ngOnInit() {
    this.authService.getUserDetails(localStorage.getItem('email')).subscribe((account: Account) => this.account = account);
  }

  showEditor() {
    this.accountToEdit = this.account;
  }

  deleteAccount() {
    this.traderService.deleteTrader(localStorage.getItem('email')).subscribe(() => {
      this.router.navigate['/logout'];

    });
  }
}
