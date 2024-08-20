import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { User } from '../../models/User';
import { Chat } from '../../models/Chat';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  focus!: User;
  myChats!: Array<Chat>;
  currentChat!: Chat;

  constructor(private chatService: ChatService) { }

  ngOnInit() {
    this.getChats();
    this.getFocus();
  }

  getChats() {
    this.chatService.getMyChats().subscribe({
      next: (res) => {
        this.myChats = res.chatList;
      }
    })
  }

  getFocus() {
    this.chatService.getChatFocus().subscribe({
      next: (userFocus) => {
        this.focus = userFocus;
        this.setFocusChat(this.focus);
      }
    });
  }

  setFocusChat(userFocus: User) {
    this.currentChat = this.myChats.find(chat => chat.participants.includes(userFocus))!;
  }
}
