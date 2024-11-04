import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { User } from '../models/User';
import { ChatService } from '../services/chat.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  navInput: boolean = false;
  addFriend: boolean = false;
  error: String | null = null;
  myUser!: User;
  showTooltipAdd: boolean = false;

  constructor(private authService: AuthService, private userService: UserService, private chatService: ChatService) { }

  ngOnInit(): void {
    if (!this.authService.getToken()) this.authService.logout();
    this.userService.getMyUser().subscribe(res => this.myUser = res.myUser);
  }

  toogleNavInput(mode: boolean) {
    this.showTooltipAdd = false
    this.navInput = !this.navInput;
    this.addFriend = mode;
  }

  submitInput(data: any) {
    this.error = null;
    let friendUsername = data.value
    this.navInput = !this.navInput;
    if (this.addFriend)
      this.userService.addFriend(friendUsername).subscribe({
        next: (res) => {
          this.chatService.addChat(res.newChat);
        }, 
        error: (res) => {
          this.error = res.error.error;
          setTimeout(() => {
            if(this.error) this.error = null;
          }, 3000);
        }
      })
  }
}
