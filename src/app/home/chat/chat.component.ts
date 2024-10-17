import { Component, Input, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { User } from '../../models/User';
import { Chat } from '../../models/Chat';
import { Message } from '../../models/Message';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  @Input() myUser!: User;
  currentChat!: Chat;
  friend!: User;

  constructor(private chatService: ChatService) { }

  ngOnInit() {
    this.initChat();
  }

  initChat(): void {
    this.getChatFocus();
  }

  getChatFocus(): void {
    this.chatService.getChatFocus().subscribe({
      next: (chat) => {
        if (chat && chat.id) {
          this.currentChat = chat;
          this.friend = this.currentChat.participants.find(user => user.id != this.myUser.id)!;
          this.chatService.connect().then(() => {
            this.chatService.joinRoom(chat.id);
          });
        }
      }
    });
  }

  setFriend(): void {

  }

  sendMessage(element: any): void {
    console.log(this.myUser.id);
    const message: String = element.value;
    const friend: User = this.currentChat.participants.filter(el => el.id != this.myUser.id).at(0)!;
    const newMessage: Message = {
      text: message,
      fromID: this.myUser.id,
      toID: friend.id,
      chatID: this.currentChat.id,
      time: new Date()
    } as Message;
    this.chatService.sendMessage(this.currentChat.id, newMessage);
    element.value = ""
  }

  getMessageAuthor(message: Message): string {
    const author: String = message.fromID == this.myUser.id ? this.myUser.username : this.friend.username;
    return author as string;
  }
}
