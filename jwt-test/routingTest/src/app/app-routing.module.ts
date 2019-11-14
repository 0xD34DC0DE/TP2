import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TraderOnlyComponent } from './components/trader-only/trader-only.component';
import { AdminOnlyComponent } from './components/admin-only/admin-only.component';

import {AuthGuardService as AuthGuard } from './services/auth-guard.service';
import {RoleGuardService as RoleGuard } from './services/role-guard.service';
import { LoginComponent } from './components/login/login.component';


const routes: Routes = [
  {
    path: 'admin', 
    component: AdminOnlyComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: 'trader', 
    component: TraderOnlyComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_TRADER'
    }
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
