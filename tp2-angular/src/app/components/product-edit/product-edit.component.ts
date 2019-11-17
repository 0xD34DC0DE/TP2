import { Component, OnInit,Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TraderService } from 'src/app/services/trader.service';
import { AuthenticationService } from 'src/app/services/authentification.service';
import {Product, editProductForm} from '../../models/product';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  userForm: FormGroup;
  @Input() product:Product;
  roleSet:string[] = [
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
  
  initForm(){
    this.userForm = this.formBuilder.group({
      id: [this.product.id,Validators.required],
      traderId:[this.product.traderId,[Validators.required, Validators.email]],
      name: [this.product.name,Validators.required],
      price: [this.product.price,Validators.required],
      availableStock: [this.product.availableStock,Validators.required],
      available: [this.product.available,Validators.required],
    });
  }



  onSubmitForm(){
    const formValue = this.userForm.value;
    const editedProduct = new editProductForm (
      formValue['id'],
      formValue['traderId'],
      formValue['name'],
      formValue['price'],
      formValue['availableStock'],
      formValue['available']
    );
    console.log(editedProduct);
    
    //this.userService.addUser(newUser);
    //this.router.navigate(['/users']);
  }
  

}

