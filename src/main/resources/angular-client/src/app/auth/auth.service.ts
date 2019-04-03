import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  AUTH_URL = "http://localhost:8090/auth";
  SIGN_IN_URL = this.AUTH_URL + "/sign-in";
  SIGN_OUT_URL = this.AUTH_URL + "/sign-out";
  CREATE_USER_URL = this.AUTH_URL + "/create-user";


  constructor(private httpClient: HttpClient,public router: Router) { }


    signIn(email: string, password: string): Observable<any>{

    return this.httpClient.post(this.SIGN_IN_URL,{
      "username": email,
      "password": password
    })
  }

  createUser(email: String, password: String): Observable<any>{

    return this.httpClient.post(this.CREATE_USER_URL,{
      "username": email,
      "password": password
    });
  }

   signOut(): Observable<any>{
    localStorage.removeItem("user");
    return this.httpClient.post(this.SIGN_OUT_URL, {});


  }

  get user(): any{
    return JSON.parse(localStorage.getItem('user'))

  }

  get token(): any{
    let user = this.user;
    if (user == null)
      return null
    else
      return user["token"];
  }
}
