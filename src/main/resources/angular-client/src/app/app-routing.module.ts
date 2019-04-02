import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {ShopListComponent} from "./shop/shop-list/shop-list.component";
import {FavoriteShopsComponent} from "./shop/favorite-shops/favorite-shops.component";
import {AuthGuard} from "./auth/auth.guard";

const routes: Routes = [

  {path: 'shops', component: ShopListComponent, canActivate: [AuthGuard]},
  {path: 'favorite-shops', component: FavoriteShopsComponent, canActivate: [AuthGuard]},
  {path: '**', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
