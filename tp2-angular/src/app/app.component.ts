import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import decode from 'jwt-decode';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private authService: AuthService){}
  title = 'tp2-angular';

  isConnected() : boolean {
    return this.authService.isAuthenticated();
  };

  isRole(role:string) : boolean {
    const token = localStorage.getItem('token');
    if(token) {
      const tokenPayload = decode(token);
      if(tokenPayload && tokenPayload.rol) {
        return tokenPayload.rol === role;
      }
    }
    return false;
  }
}
