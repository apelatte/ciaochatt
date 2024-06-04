import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../models/Chat';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private endpoint: String = "http://localhost:8080/api/users"

  constructor(private http: HttpClient) { }

  getMyChats(): Observable<any>{
    return this.http.get<Array<Chat>>(`${this.endpoint}/chats`);
  }
}
