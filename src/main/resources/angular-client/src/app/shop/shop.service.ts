import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {AuthService} from "../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  private favoriteShops: Array<any>
  private listShops: Array<any>

  constructor(private httpClient: HttpClient, private auth: AuthService) { }

  ngOnInit() {

    this.getFavoritesByUser().subscribe(data =>{
      this.favoriteShops = data
    })

    this.getAll().subscribe(data =>{
      this.listShops = data
    })
  }

  getAll(): Observable<any> {
    return this.httpClient.get('./assets/shops.json');
    // return this.httpClient.get('http://localhost:8080/shops')
}

  getFavoritesByUser(): Observable<any>{
    return this.httpClient.get('./assets/favorite-shops.json');
  //return this.httpClient.get('http://localhost:8080/users/favorite-shops/'+this.auth.user.email)
}





}
