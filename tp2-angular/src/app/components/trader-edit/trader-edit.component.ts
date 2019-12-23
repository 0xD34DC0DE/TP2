import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Account } from 'src/app/models/account';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { TraderService } from 'src/app/services/trader.service';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-trader-edit',
  templateUrl: './trader-edit.component.html',
  styleUrls: ['./trader-edit.component.css']
})
export class TraderEditComponent implements OnInit {

  userForm: FormGroup;
  @Output() saved = new EventEmitter<boolean>();
  @Input() trader: Account;
  constructor(private formBuilder: FormBuilder,
    private traderService: TraderService,
    private router: Router,
    private authService: AuthenticationService) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {

    if (this.trader) {
      this.userForm = this.formBuilder.group({
        email: [this.trader.email, [Validators.required, Validators.email]],
        firstName: [this.trader.firstName, Validators.required],
        lastName: [this.trader.lastName, Validators.required],
        phone: [this.trader.phone, Validators.required],
      });

    }
  }

  onSubmitForm() {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;
      const editedTrader = new Account(
        this.trader.email,
        formValue['firstName'],
        formValue['lastName'],
        formValue['phone'],
        'trader',
        this.trader.status
      );
      this.traderService.updateTrader(editedTrader).subscribe((acc: Account) => console.log(acc));
    }
  }

  updateTrader() {
    this.saved.emit(true);
  }


}
