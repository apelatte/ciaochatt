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
  addFriend: boolean = false;

  constructor(private authService: AuthService, private userService: UserService){}

  ngOnInit(): void {
    if(!this.authService.getToken()) this.authService.logout();
  }

  toogleNavInput(mode: boolean){
    this.navInput = !this.navInput;
    this.addFriend = mode;
  }

  submitInput(data: any){
    let friendUsername = data.value
    this.navInput = !this.navInput;
    if(this.addFriend) 
      this.userService.addFriend(friendUsername).subscribe({
        next: (res) => {
          this.userService.updateFriendList(res.friendList);
        }
      })
    
  }
}
