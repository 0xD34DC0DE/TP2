import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { Account } from 'src/app/models/account';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  accountToEdit: Account;
  account: Account;

  isEditorActive: boolean = false;

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
    this.authService.getUserDetails(localStorage.getItem('email')).subscribe((account: Account) => this.account = account);
  }

  showEditor() {
    this.accountToEdit = this.account;
  }
}
