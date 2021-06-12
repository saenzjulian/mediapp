import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';  
import { LayoutComponent } from './_pages/layout/layout.component';
import { LoginComponent } from './_pages/login/login.component'
import { Not404Component } from './_pages/not404/not404.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'pages',
    component: LayoutComponent,
    loadChildren: () => import('./_pages/pages.module').then(m => m.PagesModule)
  },
  { path: 'not-404', component: Not404Component},
  {
    path: '**',
    redirectTo: 'not-404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
