import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Chat } from '../models/Chat';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { Message } from '../models/Message';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private endpoint: String = "http://localhost:8080/api/chat"
  private chatFocus = new Subject<Chat>();
  private stompClient: any;
  private messageSubject = new BehaviorSubject<Message[]>([]);

  constructor(private http: HttpClient) {
    this.initConnectionSocket();
  }

  initConnectionSocket() {
    const url = 'http://localhost:8080/chat-socket';
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket);
  }

  joinRoom(roomId: number) {
    this.stompClient.connect({}, ()=>{
      this.stompClient.subscribe(`/topic/${roomId}`, (res: any) => {
        const response = JSON.parse(res.body);
        const message = response.body.message;
        this.messageSubject.next(message);
      })
    })
  }

  sendMessage(roomId: number, message: Message) {
    this.stompClient.send(`/app/chat/${roomId}`, {}, JSON.stringify(message))
  }

  getMyChats(): Observable<any> {
    return this.http.get<Array<Chat>>(`${this.endpoint}/my-chats`);
  }

  setChatFocus(chat: Chat): void {
    this.chatFocus.next(chat);
  }

  getChatFocus(): Observable<Chat> {
    return this.chatFocus.asObservable();
  }

  getMessageSubject(): Observable<any> {
    return this.messageSubject.asObservable();
  }

}
