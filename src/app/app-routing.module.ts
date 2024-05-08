import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { permissionsGuard } from './guards/permissions.guard';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [permissionsGuard]},
  {path: 'register', component: RegisterComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
