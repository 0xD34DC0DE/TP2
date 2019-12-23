import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TraderService } from 'src/app/services/trader.service';
import { Account} from 'src/app/models/account';

@Component({
  selector: 'app-update-trader',
  templateUrl: './update-trader.component.html',
  styleUrls: ['./update-trader.component.css']
})
export class UpdateTraderComponent implements OnInit {

  userForm: FormGroup;
  @Input() account : Account;

  constructor(private formBuilder: FormBuilder,
    private traderService: TraderService) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {

    if (this.account) {
      this.userForm = this.formBuilder.group({
        email: [this.account.email, [Validators.required, Validators.email]],
        firstName: [this.account.firstName, Validators.required],
        lastName: [this.account.lastName, Validators.required],
        phone: [this.account.phone, Validators.required],
        password: [null, Validators.nullValidator]
      });

    }
  }

  onSubmitForm() {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;
      const editedTrader = new Account(
        this.account.email,
        formValue['firstName'],
        formValue['lastName'],
        formValue['phone'],
        'ROLE_TRADER',
        this.account.status,
        formValue['password']
      );

      this.traderService.updateTrader(editedTrader).subscribe();
    }
  }

}
