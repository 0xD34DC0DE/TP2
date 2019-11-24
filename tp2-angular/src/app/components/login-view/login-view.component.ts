import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignInForm } from 'src/app/models/sign-in';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css']
})
export class LoginViewComponent implements OnInit {
  userForm: FormGroup
  constructor(private formBuilder: FormBuilder, private route: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.userForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  getErrorMessage() {
    return this.userForm.controls['email'].hasError('required') ? 'You must enter a value' :
        this.userForm.controls['email'].hasError('email') ? 'Not a valid email' :
            '';
  }

  onSubmitForm() {
    const formValue = this.userForm.value;
    const newUser = new SignInForm(
      formValue['email'],
      formValue['password']
    );

    this.authService.login(formValue['email'], formValue['password']).subscribe((success: boolean) => {
      if (success) {
        this.authService.getUserDetails(formValue['email']).subscribe();
        this.route.navigate(['/acceuil']);
      } else {
        console.log("Error could not login");
      }
    });

  }
}
