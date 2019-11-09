import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewUserComponent } from './components/new-user/new-user.component';
import { ProductViewComponent } from './components/product-view/product-view.component';
import { AcceuilComponent } from './components/acceuil/acceuil.component';
import { PageNonTrouveComponent } from './components/page-non-trouve/page-non-trouve.component';


const routes: Routes = [
  {path:'',redirectTo:'/acceuil', pathMatch: 'full'},
  {path:'new-user',component: NewUserComponent},
  {path:'catalogue',component: ProductViewComponent},
  {path:'signin',component: NewUserComponent},
  {path:'acceuil',component: AcceuilComponent},
  {path:'**',component:PageNonTrouveComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
