import { Component, Input, OnInit } from '@angular/core';
import { Chat } from '../../models/Chat';
import { ChatService } from '../../services/chat.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrl: './chat-list.component.css'
})
export class ChatListComponent implements OnInit {

  @Input() myUser!: User;
  chatList!: Array<Chat>;
  chatListLength: number = 0;
  focus!: Chat;

  constructor(private chatService: ChatService) { }

  ngOnInit(): void {
    this.initChat();
    this.listenerChats();
  }

  initChat(): void {
    this.getMyChats();
  }

  getMyChats(): void {
    this.chatService.getMyChats().subscribe({
      next: (res) => {
        this.chatList = res.chatList;
        this.focus = this.chatList.at(0)!;
        this.chatService.setChatFocus(this.focus);
        this.chatService.setChatList(this.chatList);
        this.getFocus();
        this.chatListLength = this.chatList.length;
      }
    });
  }

  listenerChats(): void {
    this.chatService.getChatList().subscribe(chatList => {
      this.chatList = chatList;
      this.chatListLength = this.chatList.length;
    })
  }

  getFocus(): void {
    this.chatService.getChatFocus().subscribe({
      next: (chat) => this.focus = chat
    });
  }

  setNewFocus(chat: Chat): void {
    this.chatService.setChatFocus(chat);
  }

  getAvatarURL(chat: any): string | null {
    const participant = chat.participants.find((el: Chat) => el.id != this.myUser.id);
    return participant ? `../../assets/${participant.avatar}.svg` : '../../assets/avatar1.svg';
  }

  getFriendUsername(chat: any): string | null { 
    const participant: User = chat.participants.find((el: Chat) => el.id != this.myUser.id);
    return participant ? `${participant.username}` : null;
  }
}
