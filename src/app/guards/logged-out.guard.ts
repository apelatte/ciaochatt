import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const loggedOutGuard: CanActivateFn = (route, state) => {
  
  const authService = inject(AuthService);
  const router = inject(Router);
  
  let isLoggedIn: boolean = authService.isLoggedIn();

  return isLoggedIn ? router.navigate(["/"]) : true;
};
