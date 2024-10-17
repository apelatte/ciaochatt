import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private endpoint: String = "http://localhost:8080/api/users"
  private friendList = new Subject<Array<User>>();

  constructor(private http: HttpClient) { }

  addFriend(friend: String): Observable<any> {
    return this.http.post(`${this.endpoint}/add-friend`, friend);
  }

  getFriends(): Observable<any> {
    return this.http.get(`${this.endpoint}/friends`);
  }

  updateFriendList(newFriendList: Array<User>): void {
    this.friendList.next(newFriendList);
  }

  getFriendList(): Observable<Array<User>> {
    return this.friendList.asObservable();
  }

  getMyUser(): Observable<any> {
    return this.http.get<any>(`${this.endpoint}/my-user`);
  }
  
}
