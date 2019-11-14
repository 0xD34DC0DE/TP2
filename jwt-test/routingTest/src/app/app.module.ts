import { BrowserModule } from '@angular/platform-browser';
import { JwtModule } from "@auth0/angular-jwt";
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminOnlyComponent } from './components/admin-only/admin-only.component';
import { TraderOnlyComponent } from './components/trader-only/trader-only.component';
import { TestingComponent } from './components/testing/testing.component';
import { HttpClientModule } from '@angular/common/http';

import { AuthGuardService as AuthGuard } from './services/auth-guard.service';
import { RoleGuardService as RoleGuard } from './services/role-guard.service';
import { LoginComponent } from './components/login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminOnlyComponent,
    TraderOnlyComponent,
    TestingComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem("token");
        },
        whitelistedDomains: ["localhost:8081"],
        blacklistedRoutes: ["localhost:8081/api/auth/signin", "localhost:8081/api/auth/register"]
      }
    })
  ],
  providers: [AuthGuard, RoleGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
