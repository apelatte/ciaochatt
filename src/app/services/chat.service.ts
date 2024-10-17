import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
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
  private isConnected!: boolean;

  constructor(private http: HttpClient) {
    this.initConnectionSocket();
  }

  initConnectionSocket() {
    const url = 'http://localhost:8080/chat-socket';
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket);
  }

  connect(): Promise<void> {
    return new Promise((resolve, reject) => {
      if (this.isConnected) {
        resolve(); // Si ya está conectado, resolver inmediatamente
        return;
      }

      const socket = new SockJS('http://localhost:8080/chat-socket'); // URL del WebSocket
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect({}, (frame: any) => {
        console.log('Conectado: ' + frame);
        this.isConnected = true;
        resolve(); // Resolver la promesa cuando la conexión esté establecida
      }, (error: any) => {
        console.error('Error al conectar WebSocket:', error);
        reject(error); // Rechazar la promesa si ocurre un error en la conexión
      });
    });
  }

  joinRoom(roomId: number): void {
    if (this.isConnected) {
      this.stompClient.subscribe(`/topic/chat/${roomId}`, (message: any) => {
        console.log(`Joined room ${roomId}, Message:`, message.body);
      });
    } else {
      console.error('No se puede unir a la room. No hay conexión establecida.');
    }
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

}
