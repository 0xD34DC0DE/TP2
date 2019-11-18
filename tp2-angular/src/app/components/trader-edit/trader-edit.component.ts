import { Component, OnInit, Input } from '@angular/core';
import { Account } from 'src/app/models/account';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { TraderService } from 'src/app/services/trader.service';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { editProductForm } from 'src/app/models/product';

@Component({
  selector: 'app-trader-edit',
  templateUrl: './trader-edit.component.html',
  styleUrls: ['./trader-edit.component.css']
})
export class TraderEditComponent implements OnInit {

  userForm: FormGroup;
  @Input() trader: Account;
  constructor(private formBuilder: FormBuilder,
    private traderService: TraderService,
    private router: Router,
    private authService: AuthenticationService) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    console.log("Inside initForm")
    if (this.trader) {
      this.userForm = this.formBuilder.group({
        email: [this.trader.email, [Validators.required, Validators.email]],
        firstName: [this.trader.firstName, Validators.required],
        lastName: [this.trader.lastName, Validators.required],
        address: [this.trader.address, Validators.required],
        phone: [this.trader.phone, Validators.required],
      });
      console.log(this.userForm)
    }
  }



  onSubmitForm() {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;
      const editedTrader = new Account(
        this.trader.email,
        formValue['firstName'],
        formValue['lastName'],
        formValue['address'],
        formValue['phone'],
        ['ROLE_TRADER'],
        this.trader.status
      );

      this.traderService.updateTrader(editedTrader).subscribe();
    }
  }


}
