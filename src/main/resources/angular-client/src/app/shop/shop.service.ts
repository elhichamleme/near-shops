import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { AuthService } from "../auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  private SHOPS_URL = "http://localhost:8090/shops";
  private NEAR_SHOPS_URL = this.SHOPS_URL + "/near";
  private PREFERRED_SHOPS_URL = this.SHOPS_URL + "/preferred";
  private LIKE_SHOPS_URL = this.SHOPS_URL + "/like";
  private DISLIKE_SHOPS_URL = this.SHOPS_URL + "/dislike";
  private REMOVE_FROM_PREFERRED = this.SHOPS_URL + "/remove-from-preferred";

  constructor(private httpClient: HttpClient, private auth: AuthService) { }


  nearShops(latitude: number, longitude: number): Observable<any> {

    return this.httpClient.get(this.NEAR_SHOPS_URL+"/"+latitude+"/"+longitude);
}

  preferredShops(): Observable<any>{
    return this.httpClient.get(this.PREFERRED_SHOPS_URL)
}

  likeShop(shopId: string){
    return this.httpClient.get(this.LIKE_SHOPS_URL + "/" + shopId);
  }

  dislikeShop(shopId: string){
    return this.httpClient.get(this.DISLIKE_SHOPS_URL + "/" + shopId);
  }

  removeFromPreferred(shopId: string){
    return this.httpClient.get(this.REMOVE_FROM_PREFERRED + "/" + shopId);
  }




}
