import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, Subscription } from 'rxjs';
import { Chat } from '../models/Chat';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { Message } from '../models/Message';
import { environment } from '../../enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private endpoint: String = "http://localhost:8080/api/chat"
  private chatList = new Array<Chat>();
  private chatListObs = new Subject<Chat[]>();
  private chatFocus = new Subject<Chat>();
  private stompClient: any;
  private messageSubject = new BehaviorSubject<Message[]>([]);
  private isConnected: boolean = false;
  private currentSubscription: Subscription | null = null;
  private socketUrl = 'http://localhost:8080/chat-socket'

  constructor(private http: HttpClient) {
  }

  initConnectionSocket() {
    const socket = new SockJS(this.socketUrl);
    this.stompClient = Stomp.over(socket);
  }

  connectSocket(onConnected?: () => void): void {
    if (!this.isConnected) {
      const socket = new SockJS(this.socketUrl);
      this.stompClient = Stomp.over(socket);
      if (environment.production) this.stompClient.debug = () => { }; // Disable debugging in production
      this.stompClient.connect({}, () => {
        this.isConnected = true;
        if (onConnected) {
          onConnected(); // Execute the callback once connected
        }
      });
    } else if (onConnected) {
      onConnected(); // If already connected, execute the callback immediately
    }
  }

  disconnectSocket(): void {
    this.stompClient.disconnect(() => {
      this.isConnected = false
      this.currentSubscription?.unsubscribe();
      this.currentSubscription = null
      this.stompClient = null;
    });
  }

  joinRoom(roomId: number) {
    if (this.isConnected) {
      this.currentSubscription?.unsubscribe();
      this.currentSubscription = this.stompClient.subscribe(`/topic/${roomId}`, (res: any) => {
        const response = JSON.parse(res.body);
        const message = response.body.message;
        this.messageSubject.next(message);
      })
    }
  }

  sendMessage(roomId: number, message: Message) {
    this.stompClient.send(`/app/chat/${roomId}`, {}, JSON.stringify(message))
  }

  getMyChats(): Observable<any> {
    return this.http.get<Array<Chat>>(`${this.endpoint}/my-chats`);
  }

  addChat(chat: Chat): void {
    this.chatList.push(chat)
    this.chatListObs.next(this.chatList);
  }

  setChatList(chatList: Chat[]): void {
    this.chatList = chatList;
    this.chatListObs.next(this.chatList);
  }

  getChatList(): Observable<Chat[]> {
    return this.chatListObs.asObservable();
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
