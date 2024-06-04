import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private endpoint: String = "http://localhost:8080/api/users"

  constructor(private http: HttpClient) { }

  addFriend(friend: String): Observable<any> {
    return this.http.post(`${this.endpoint}/add-friend`, friend);
  }

  getMyUser(): Observable<User> {
    return this.http.get<User>(`${this.endpoint}/my-user`);
  }

  getFriends(): Observable<any> {
    return this.http.get(`${this.endpoint}/friends`);
  }
}
