import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  loggedIn!: Observable<boolean>;

  constructor(private authService: AuthService, private router: Router){

  }

  ngOnInit(){
    this.authService.loggedSub = this.authService.isLoggedIn();
    this.loggedIn = this.authService.loggedSub;
  }

  logout(): void {
    this.authService.logout()
    this.router.navigate(["/login"]);
  }
}
