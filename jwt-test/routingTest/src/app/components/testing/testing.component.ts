import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { User } from 'src/app/models/User';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.css']
})
export class TestingComponent implements OnInit {

  private currentUser: string = "Logged out";

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

  loginAdmin() {
    this.authService.login("admin@tp2.com", "reallyStrongPassword").subscribe((success: boolean) => {
      if (success) {
        this.authService.getUserDetails("admin@tp2.com").pipe(map((userDetails: User) => {
          this.currentUser = userDetails.email;
        })).subscribe();
      } else{
        console.error("Looks like there is and error...");
      }
    });
  }

  loginTrader() {
    this.authService.login("emerick123@tp2.com", "reallyStrongPassword").subscribe((success: boolean) => {
      if (success) {
        this.authService.getUserDetails("emerick123@tp2.com").pipe(map((userDetails: User) => {
          this.currentUser = userDetails.email;
        })).subscribe();
      } else{
        console.error("Looks like there is and error...");
      }
    });
  }

  logout() {
    this.authService.logout();
    this.currentUser = "Logged out";
  }

}