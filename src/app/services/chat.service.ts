import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Chat } from '../models/Chat';
import SockJS from 'sockjs-client';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { Message } from '../models/Message';

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

  constructor(private http: HttpClient) {
    this.initConnectionSocket();
  }

  initConnectionSocket() {
    const url = 'http://localhost:8080/chat-socket';
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket);
  }

  connectSocket(): void {
    if(!this.isConnected) this.stompClient.connect({}, () => { this.isConnected = true });
  }

  disconnectSocket(): void {
    this.stompClient.disconnect({}, () => { this.isConnected = false });
  }

  joinRoom(roomId: number) {
    if (this.isConnected) {
      this.stompClient.subscribe(`/topic/${roomId}`, (res: any) => {
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
