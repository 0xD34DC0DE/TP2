import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TraderService } from 'src/app/services/trader.service';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { Product, editProductForm } from '../../models/product';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  userForm: FormGroup;
  @Input() product: Product;


  constructor(private formBuilder: FormBuilder,
    private traderService: TraderService,
    private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    if (this.product) {
      this.userForm = this.formBuilder.group({
        id: [this.product.id, Validators.required],
        name: [this.product.name, Validators.required],
        price: [this.product.price, Validators.required],
        description: [this.product.description, Validators.required],
        hidden: [this.product.hidden, Validators.required],
      });
    }

  }

  onSubmitForm() {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;

      const editedProduct = new editProductForm(
        this.product.id,
        formValue['name'],
        formValue['price'],
        formValue['description'],
        formValue['hidden']
      );

      this.traderService.updateProduct(localStorage.getItem('email'), editedProduct).subscribe((product: Product) => {

        this.router.navigate(['/tradermanageproducts']);
      });

    }
  }
}

