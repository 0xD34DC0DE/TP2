import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../models/User';
import { environment } from 'src/environments/environment';
import { JwtResponse } from '../models/responses/jwtResponse';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {


  constructor(private http: HttpClient, public auth: AuthService) { 
  }

  login(email: string, password: string): Observable<boolean> {
    return this.getToken(email, password).pipe(
      map(response => {
        if (response.accessToken) {
          localStorage.setItem('token', response.accessToken); // Set the token for jwt-helper
          return true;
        }
        return false;
      }));
  }

  getToken(email: string, password: string): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${environment.authApiUrl}/signin`, { username: email, password: password });
  }

  getUserDetails(email: string): Observable<User> {
    //angular2-jwt should inject the JWT token in the request
    if(this.auth.isAuthenticated()) {
      return this.http.get<User>(`${environment.adminUrl}/${email}`);
    }

  }

  logout() {
    // remove token from local storage to log user out
    localStorage.removeItem('token');
  }
}
