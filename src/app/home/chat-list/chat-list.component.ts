import { Component, OnInit } from '@angular/core';
import { Chat } from '../../models/Chat';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrl: './chat-list.component.css'
})
export class ChatListComponent implements OnInit{

  chatList!: Array<Chat>;
  friends!: Array<User>;

  constructor(private userService: UserService){}

  ngOnInit(): void {
    this.userService.getFriends().subscribe({
      next: (res) => {
        this.friends = res.friends;
        console.log(this.friends)
      }
    });
  }

}
