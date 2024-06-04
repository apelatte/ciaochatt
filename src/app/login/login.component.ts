import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginRequest } from '../auth/LoginRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../../assets/styles/form-style.css']
})
export class LoginComponent {

  loginRequest!: LoginRequest;
  myForm!: FormGroup;

  constructor(private authService: AuthService, private fb: FormBuilder, private router: Router) {
    this.myForm = this.fb.group({
      username: ['', [Validators.minLength(4), Validators.maxLength(50), Validators.required]],
      password: ['', Validators.required]
    });
  }

  login(): void {
    if (this.myForm.valid) {
      this.loginRequest = this.myForm.value;
      this.authService.login(this.loginRequest).subscribe({
        next: (res) => {
          this.authService.setToken(res.token)
          this.router.navigate(["/"]);
        }
      });
    }
  }
}
