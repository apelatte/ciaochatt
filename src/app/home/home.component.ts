import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  navInput: boolean = false;
  inputMode!: String;

  constructor(private authService: AuthService, private userService: UserService){}

  ngOnInit(): void {
    if(!this.authService.getToken()) this.authService.logout();
  }

  toogleNavInput(inputMode: String){
    this.navInput = !this.navInput;
    this.inputMode = inputMode;
  }

  submitInput(data: any){
    let friendUsername = data.value
    this.navInput = !this.navInput;
    if(this.inputMode == 'add') 
      this.userService.addFriend(friendUsername).subscribe({
        next: (res) => {
          console.log(res);
        }
      })
    
  }
}
