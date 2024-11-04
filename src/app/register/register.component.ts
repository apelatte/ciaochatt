import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterRequest } from '../auth/RegisterRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css', '../../assets/styles/form-style.css']
})
export class RegisterComponent implements OnInit {

  myForm!: FormGroup;
  passwordsMatches: boolean = true;
  errorPasswordLength: boolean = false;
  error: String | null = null;

  constructor(private authService: AuthService, private fb: FormBuilder, private router: Router) {
    this.myForm = this.fb.group({
      username: ['', [Validators.minLength(4), Validators.maxLength(20), Validators.required]],
      password: ['', [Validators.minLength(6), Validators.maxLength(50), Validators.required]],
      repassword: ['', [Validators.minLength(6), Validators.maxLength(50), Validators.required]]
    })
  }

  ngOnInit(): void {}

  register(): void {
    this.resetErrors();

    this.passwordsMatches = this.myForm.get('password')!.value == this.myForm.get('repassword')!.value;

    if (this.myForm.valid && this.passwordsMatches) {
      let newUser: RegisterRequest = {
        username: this.myForm.get('username')!.value,
        password: this.myForm.get('password')!.value,
        avatar: 'avatar1'
      }
      this.authService.register(newUser).subscribe({
        next: () => this.router.navigate(["/login"]),
        error: (res) => this.error = res.error.error
      });
    }
    else if (!this.passwordsMatches) this.passwordsMatches = false;
    else if (this.myForm.get('password')!.hasError('minlength')) this.errorPasswordLength = true;
  }

  resetErrors(): void {
    this.passwordsMatches = true;
    this.errorPasswordLength = false;
    this.error = null;
  }
}
