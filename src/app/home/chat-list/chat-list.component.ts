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
  focus!: Chat;

  constructor(private chatService: ChatService) { }

  ngOnInit(): void {
    this.getMyChats();
  }

  getMyChats(): void {
    this.chatService.getMyChats().subscribe({
      next: (res) => {
        this.chatList = res.chatList
        this.focus = this.chatList.at(0)!;
      }
    });
  }

  setNewFocus(chat: Chat): void {
    this.chatService.setChatFocus(chat);
  }

}
