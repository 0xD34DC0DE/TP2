import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Account } from '../models/account';
import { environment } from 'src/environments/environment';
import { JwtResponse } from '../models/responses/jwt-response';
import { AuthService } from './auth.service';

import { Router } from '@angular/router';
import { SignUpForm } from '../models/sign-up';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {


  constructor(private http: HttpClient, public auth: AuthService, private route: Router) {
  }

  login(email: string, password: string): Observable<boolean> {
    return this.getToken(email, password).pipe(
      map(response => {
        if (response && response.accessToken) {
          localStorage.setItem('token', response.accessToken); // Set the token for jwt-helper
          console.log(response.accessToken);
          return true;
        }
        return false;
      }));
  }

  getToken(email: string, password: string): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${environment.authApiUrl}/signin`, { username: email, password: password });
  }

  getUserDetails(email: string): Observable<Account> {
    //angular2-jwt should inject the JWT token in the request
    if (this.auth.isAuthenticated()) {
      return this.http.get<Account>(`${environment.adminUrl}/${email}`).pipe(map((account: Account) => {
        localStorage.setItem('email', account.email); // Set the email
        console.log(account);

        return account;
      }));
    }

  }

  signup(form: SignUpForm) : Observable<string> {
    return this.http.post<string>(`${environment.authApiUrl}/signup`, form);
  }

  logout() {
    // remove token from local storage to log user out
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    this.route.navigate(['/acceuil']);
  }
}
