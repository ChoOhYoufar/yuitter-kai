import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MyPageComponent } from './components/my-page/my-page.component';
import { ModuleWithProviders } from '@angular/core';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'my-page', component: MyPageComponent }
];

export const ROUTES: ModuleWithProviders = RouterModule.forRoot(routes);
