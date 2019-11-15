import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AcceuilComponent } from './components/acceuil/acceuil.component';
import { PageNonTrouveComponent } from './components/page-non-trouve/page-non-trouve.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginViewComponent } from './components/login-view/login-view.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ManagingComponent } from './components/managing/managing.component';
import { ManageAdminComponent } from './components/manage-admin/manage-admin.component';
import { ManageProductsComponent } from './components/manage-products/manage-products.component';
import { ManageTradersComponent } from './components/manage-traders/manage-traders.component';
import { LogoutViewComponent } from './components/logout-view/logout-view.component';


const routes: Routes = [
  {path:'',redirectTo:'/acceuil', pathMatch: 'full'},
  {path:'catalogue',component: CatalogueComponent},
  {path:'signup',component: SignupComponent},
  {path:'login',component: LoginViewComponent},
  {path:'logout',component: LogoutViewComponent},
  {path:'acceuil',component: AcceuilComponent},
  {path:'catalogue',component: AcceuilComponent},
  {path:'profile',component: ProfileComponent},
  {path:'managing',component: ManagingComponent},
  {path:'manageadmin',component: ManageAdminComponent},
  {path:'manageproducts',component: ManageProductsComponent},
  {path:'managetraders',component: ManageTradersComponent},
  {path:'**',component:PageNonTrouveComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
