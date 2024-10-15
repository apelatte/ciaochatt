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
    
  }

}
