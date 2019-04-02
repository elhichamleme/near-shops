import {HttpClientModule} from "@angular/common/http";
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ShopListComponent} from './shop/shop-list/shop-list.component';
// import { InfiniteScrollModule} from "ngx-infinite-scroll";
import {LoginComponent} from './login/login.component';
import {NavbarComponent} from './navbar/navbar.component';
import {FavoriteShopsComponent} from './shop/favorite-shops/favorite-shops.component'


@NgModule({
  declarations: [
    AppComponent,
    ShopListComponent,
    LoginComponent,
    NavbarComponent,
    FavoriteShopsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
