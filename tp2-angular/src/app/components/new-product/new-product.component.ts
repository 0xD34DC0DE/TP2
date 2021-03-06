import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TraderService } from 'src/app/services/trader.service';
import { Product, NewProduct } from '../../models/product';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private traderService: TraderService,
    private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
      this.userForm = this.formBuilder.group({
        name: ['', Validators.required],
        price: ['', Validators.required],
        description: ['', Validators.required],
        hidden: ['', Validators.required],
      });
  
  }

  onSubmitForm() {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;

      const newProduct = new NewProduct(
        formValue['name'],
        formValue['price'],
        formValue['description'],
        formValue['hidden']
      );

      this.traderService.createProduct(localStorage.getItem('email'), newProduct).subscribe((product: Product) => {

        this.router.navigate(['/tradermanageproducts']);
      });

    }
  }
}

