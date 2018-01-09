import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MyPageComponent } from './components/my-page/my-page.component';
import { ModuleWithProviders } from '@angular/core';
import { SecureGuard } from './guards/secure.guard';
import { SignInFormComponent } from './components/sign-in-form/sign-in-form.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [SecureGuard],
  },
  {
    path: 'my-page',
    component: MyPageComponent,
    canActivate: [SecureGuard],
  },
  {
    path: 'signin',
    component: SignInFormComponent
  }
];

export const ROUTES: ModuleWithProviders = RouterModule.forRoot(routes);
