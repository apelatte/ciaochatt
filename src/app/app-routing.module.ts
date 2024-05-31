import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { permissionsGuard } from './guards/permissions.guard';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { loggedOutGuard } from './guards/logged-out.guard';

const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full', canActivate: [permissionsGuard]},
  {path: 'login', component: LoginComponent, pathMatch: 'full', canActivate: [loggedOutGuard]},
  {path: 'register', component: RegisterComponent, pathMatch: 'full', canActivate: [loggedOutGuard]},
  {path: '**', component: HomeComponent, canActivate: [permissionsGuard]}
];


/* const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full'},
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path: 'register', component: RegisterComponent, pathMatch: 'full'},
  {path: '**', component: HomeComponent}
]; */

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
