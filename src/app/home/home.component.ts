import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  navInput: boolean = false;

  constructor(private authService: AuthService){}

  ngOnInit(): void {
    if(!this.authService.getToken()) this.authService.logout();
  }

  toogleNavInput(){
    this.navInput = !this.navInput;
  }
}
