import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {NearShopsComponent} from "./shop/near-shops/near-shops.component";
import {FavoriteShopsComponent} from "./shop/favorite-shops/favorite-shops.component";
import {AuthGuard} from "./auth/auth.guard";

const routes: Routes = [

  {path: 'near-shops', component: NearShopsComponent, canActivate: [AuthGuard]},
  {path: 'preferred-shops', component: FavoriteShopsComponent, canActivate: [AuthGuard]},
  {path: '**', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
