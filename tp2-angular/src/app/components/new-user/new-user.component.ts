import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { TraderService } from '../../services/trader.service';
import { Router } from '@angular/router';
import { SignUpForm } from '../../models/sign-up';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {
  
  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private traderService: TraderService,
              private router: Router) { }

  ngOnInit() {
    this.initForm();
  }
  
  initForm(){
    this.userForm = this.formBuilder.group({
      firstName: ['',Validators.required],
      lastName: ['',Validators.required],
      email:['',[Validators.required, Validators.email]],
      password: ['',Validators.required],
      adresse: ['',Validators.required],
      phone: ['',Validators.required],
      role: ['',Validators.required],
    });
  }



  onSubmitForm(){
    const formValue = this.userForm.value;
    const newUser = new SignUpForm(
      formValue['firstName'],
      formValue['lastName'],
      formValue['email'],
      formValue['password'],
      formValue['adresse'],
      formValue['phone'],
      formValue['role']
    );
    //this.userService.addUser(newUser);
    this.router.navigate(['/users']);
  }

  getHobbies(){
    return this.userForm.get('hobbies') as FormArray;
  }

  onAddHobby(){
    const newHobbyControl = this.formBuilder.control('',Validators.required);
    this.getHobbies().push(newHobbyControl);
  }
}
