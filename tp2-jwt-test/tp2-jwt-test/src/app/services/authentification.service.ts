import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map, flatMap, first } from 'rxjs/operators';

import { User } from '../models/User';
import { environment } from 'src/environments/environment';
import { JwtResponse } from '../models/responses/jwtResponse';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(email: string, password: string) : Observable<User> {
    return this.getToken(email, password).pipe(
      flatMap(response => {
        return this.getUserDetails(email, response.accessToken).pipe(map((response: User) =>  response))
      }));
  }

  getToken(email: string, password: string): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${environment.authApiUrl}/signin`, { username: email, password: password });
  }

  getUserDetails(email: string, token: string) : Observable<User> {
    const requestOptions = {
      headers: new HttpHeaders({
        "Authorization":  `Bearer ${token}`
      })
    }
    return this.http.get<User>(`${environment.adminUrl}/${email}`, requestOptions);
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
