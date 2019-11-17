import { Component, OnInit,Input } from '@angular/core';
import { Traders, Account, AccountForm } from 'src/app/models/account';
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

  userForm:FormGroup;
  @Input() trader:Traders;
  constructor(private formBuilder: FormBuilder,
    private traderService: TraderService,
    private router: Router,
    private authService: AuthenticationService) { }

ngOnInit() {
this.initForm();
}

initForm(){
this.userForm = this.formBuilder.group({
email:[this.trader.email,[Validators.required, Validators.email]],
firstName: [this.trader.firstName,Validators.required],
lastName: [this.trader.lastName,Validators.required],
address: [this.trader.address,Validators.required],
phone: [this.trader.phone,Validators.required],
});
}



onSubmitForm(){
const formValue = this.userForm.value;
const editedProduct = new AccountForm (
formValue['email'],
formValue['firstName'],
formValue['lastName'],
formValue['address'],
formValue['phone']
);
console.log(editedProduct);

//this.userService.addUser(newUser);
//this.router.navigate(['/users']);
}


}
