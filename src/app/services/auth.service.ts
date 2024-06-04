import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from '../auth/LoginRequest';
import { BehaviorSubject, Observable } from 'rxjs';
import { RegisterRequest } from '../auth/RegisterRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private endpoint: String = "http://localhost:8080/auth"
  private token!: String;
  private loggedSub$ = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
  }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post(`${this.endpoint}/login`, loginRequest);
  }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post(`${this.endpoint}/sign-up`, registerRequest);
  }

  setToken(token: String): void {
    this.token = token;
    localStorage.setItem("token", this.token as string);
    this.loggedSub$.next(true);
  }

  getToken(): String | boolean {
    if(localStorage) return localStorage.getItem("token") as String ;
    return false;
  }

  logout(): void {
    localStorage.removeItem("token");
    this.loggedSub$.next(false);
  }

  isLoggedIn(): boolean {
    let response = localStorage.getItem("token") ? true : false;
    return response;
  }

  get loggedSub(): Observable<any> {
    return this.loggedSub$.asObservable();
  }

  set loggedSub(isLoggedIn: boolean){
    this.loggedSub$.next(isLoggedIn);
  }
}
