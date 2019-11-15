import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-logout-view',
  templateUrl: './logout-view.component.html',
  styleUrls: ['./logout-view.component.css']
})
export class LogoutViewComponent implements OnInit {

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
    this.authService.logout();
  }

}
