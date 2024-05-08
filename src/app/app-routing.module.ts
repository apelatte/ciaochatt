import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { permissionsGuard } from './guards/permissions.guard';

const routes: Routes = [
  {path: '', component: LoginComponent, canActivate: [permissionsGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
