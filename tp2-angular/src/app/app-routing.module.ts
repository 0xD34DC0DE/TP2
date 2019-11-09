import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductViewComponent } from './components/product-view/product-view.component';
import { AcceuilComponent } from './components/acceuil/acceuil.component';
import { PageNonTrouveComponent } from './components/page-non-trouve/page-non-trouve.component';
import { SignupComponent } from './components/signup/signup.component';


const routes: Routes = [
  {path:'',redirectTo:'/acceuil', pathMatch: 'full'},
  {path:'catalogue',component: ProductViewComponent},
  {path:'signup',component: SignupComponent},
  {path:'acceuil',component: AcceuilComponent},
  {path:'**',component:PageNonTrouveComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
