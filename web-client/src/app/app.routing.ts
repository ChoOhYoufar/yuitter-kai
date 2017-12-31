import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MyPageComponent } from './components/my-page/my-page.component';
import { ModuleWithProviders } from '@angular/core';
import { SecureGuard } from './guards/secure.guard';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [SecureGuard],
  },
  {
    path: 'my-page',
    component: MyPageComponent,
    canActivate: [SecureGuard],
  }
];

export const ROUTES: ModuleWithProviders = RouterModule.forRoot(routes);
