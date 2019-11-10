import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AcceuilComponent } from './components/acceuil/acceuil.component';
import { PageNonTrouveComponent } from './components/page-non-trouve/page-non-trouve.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginViewComponent } from './components/login-view/login-view.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';


const routes: Routes = [
  {path:'',redirectTo:'/acceuil', pathMatch: 'full'},
  {path:'catalogue',component: CatalogueComponent},
  {path:'signup',component: SignupComponent},
  {path:'login',component: LoginViewComponent},
  {path:'acceuil',component: AcceuilComponent},
  {path:'catalogue',component: AcceuilComponent},
  {path:'**',component:PageNonTrouveComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
