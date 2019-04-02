import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NearShopsComponent} from './shop/near-shops/near-shops.component';
import {LoginComponent} from './login/login.component';
import {NavbarComponent} from './navbar/navbar.component';
import {FavoriteShopsComponent} from './shop/favorite-shops/favorite-shops.component'
import {TokenInterceptor} from "./auth/token.interceptor";


@NgModule({
  declarations: [
    AppComponent,
    NearShopsComponent,
    LoginComponent,
    NavbarComponent,
    FavoriteShopsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
