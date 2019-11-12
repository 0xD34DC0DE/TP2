import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { User } from 'src/app/models/User';
import { JwtResponse } from 'src/app/models/responses/jwtResponse';

@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.css']
})
export class TestingComponent implements OnInit {

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

  login() {
    console.log("wat");
    //this.authService.getToken("admin@tp2.com", "reallyStrongPassword").subscribe((res: JwtResponse) => console.log(res));
  
    this.authService.login("admin@tp2.com", "reallyStrongPassword").subscribe((user: User) => console.log(user));
  }

}
