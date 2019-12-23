import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AcceuilComponent } from './components/acceuil/acceuil.component';
import { PageNonTrouveComponent } from './components/page-non-trouve/page-non-trouve.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginViewComponent } from './components/login-view/login-view.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ManagingComponent } from './components/managing/managing.component';
import { TraderManageProductComponent } from './components/trader-manage-product/trader-manage-product.component';
import { ManageProductsComponent } from './components/managing/manage-products/manage-products.component';
import { ManageTradersComponent } from './components/managing/manage-traders/manage-traders.component';


const routes: Routes = [
  {path:'',redirectTo:'/acceuil', pathMatch: 'full'},
  {path:'catalogue',component: CatalogueComponent},
  {path:'signup',component: SignupComponent},
  {path:'login',component: LoginViewComponent},
  {path:'acceuil',component: AcceuilComponent},
  {path:'catalogue',component: AcceuilComponent},
  {path:'tradermanageproducts',component: TraderManageProductComponent},
  {path:'profile',component: ProfileComponent},
  {path:'managing',component: ManagingComponent},
  {path:'manageproducts',component: ManageProductsComponent},
  {path:'managetraders',component: ManageTradersComponent},
  {path:'**',component:PageNonTrouveComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
