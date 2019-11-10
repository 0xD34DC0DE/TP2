import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignInForm } from 'src/app/models/sign-in';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css']
})
export class LoginViewComponent implements OnInit {
  userForm:FormGroup
  constructor(private formBuilder: FormBuilder,private route:Router) { }

  ngOnInit() {
    this.initForm();
  }

  initForm(){
    this.userForm = this.formBuilder.group({
      email:['',[Validators.required, Validators.email]],
      password:['',Validators.required]
    });
  }

  onSubmitForm(){
    const formValue = this.userForm.value;
    const newUser = new SignInForm(
      formValue['email'],
      formValue['password']
    );
    console.log(newUser);
    
    //this.userService.addUser(newUser);
    //this.router.navigate(['/users']);
  }
}
