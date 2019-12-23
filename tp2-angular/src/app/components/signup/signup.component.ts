import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { TraderService } from '../../services/trader.service';
import { Router } from '@angular/router';
import { SignUpForm } from '../../models/sign-up';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { MustMatch } from 'src/must-match-validator';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  submitted = false;
  creationSuccessful = true;
  userForm: FormGroup;
  roleSet: string[] = [
    'client',
    'trader'
  ]

  constructor(private formBuilder: FormBuilder,
    private traderService: TraderService,
    private router: Router,
    private authService: AuthenticationService) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.userForm = this.formBuilder.group({
      title: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      phone: ['', Validators.required],
      acceptTerms: ['', Validators.required],
      role: [''],
    }, {
      validator: MustMatch('password', 'confirmPassword')
  });
  }

  get f() { return this.userForm.controls; }

  onSubmit() {
    this.submitted = true;
    
    if (this.userForm.invalid) {
      return;
    }
    const formValue = this.userForm.value;
    const newUser = new SignUpForm(
      formValue['firstName'],
      formValue['lastName'],
      formValue['email'],
      formValue['password'],
      formValue['phone'],
      'trader'
    );
    console.log(newUser);

    this.authService.signup(newUser).subscribe((message: string) => {
      console.log(message);
      this.router.navigate(['/acceuil']);
    },error => {
      this.creationSuccessful = true;
      if (error.error == 'Fail -> Email is already in use!') {
        alert('Email already in use');
      }
    }
    );

    if (this.creationSuccessful) {
      this.router.navigate(['/login']);
    }
  }

  onReset() {
    this.submitted = false;
    this.userForm.reset();
  }
}
