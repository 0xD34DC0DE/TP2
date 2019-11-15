import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { JwtModule } from "@auth0/angular-jwt";
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductViewComponent } from './components/product-view/product-view.component';
import { AcceuilComponent } from './components/acceuil/acceuil.component';
import { PageNonTrouveComponent } from './components/page-non-trouve/page-non-trouve.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginViewComponent } from './components/login-view/login-view.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ManagingComponent } from './components/managing/managing.component';
import { ManageAdminComponent } from './components/manage-admin/manage-admin.component';
import { ManageTradersComponent } from './components/manage-traders/manage-traders.component';
import { ManageProductsComponent } from './components/manage-products/manage-products.component';
import { LogoutViewComponent } from './components/logout-view/logout-view.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductViewComponent,
    AcceuilComponent,
    PageNonTrouveComponent,
    SignupComponent,
    LoginViewComponent,
    CatalogueComponent,
    FooterComponent,
    ProfileComponent,
    ManagingComponent,
    ManageAdminComponent,
    ManageTradersComponent,
    ManageProductsComponent,
    LogoutViewComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
