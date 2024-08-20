import { Component, OnInit } from '@angular/core';
import { Chat } from '../../models/Chat';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';
import { ChatService } from '../../services/chat.service';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrl: './chat-list.component.css'
})
export class ChatListComponent implements OnInit {

  chatList!: Array<Chat>;
  friends!: Array<User>;
  focus!: User;

  constructor(private userService: UserService, private chatService: ChatService) { }

  ngOnInit(): void {
    this.getFriends();
    this.userService.getFriendList().subscribe({
      next: (res) => {
        this.friends = res;
        this.setFocusChat();
      }
    });
    this.getFocusChat();
  }

  getFriends() {
    this.userService.getFriends().subscribe({
      next: (res) => {
        this.userService.updateFriendList(res.friends);
      }
    });
  }

  setFocusChat(): void {
    if(this.friends.length > 0) this.chatService.setChatFocus(this.friends.at(0)!);
  }

  setNewFocus(user: User) {
    this.chatService.setChatFocus(user);
  }

  getFocusChat(){
    this.chatService.getChatFocus().subscribe({
      next: (res) => {
        this.focus = res;
      }
    })
  }

}
