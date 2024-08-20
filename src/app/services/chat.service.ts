import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Chat } from '../models/Chat';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private endpoint: String = "http://localhost:8080/api/users"
  private chatFocus = new Subject<User>();

  constructor(private http: HttpClient) { }

  getMyChats(): Observable<any> {
    return this.http.get<Array<Chat>>(`${this.endpoint}/chats`);
  }

  setChatFocus(user: User): void {
    this.chatFocus.next(user);
  }

  getChatFocus(): Observable<User>{
    return this.chatFocus.asObservable();
  }
}
