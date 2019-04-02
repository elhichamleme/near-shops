import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {ShopService} from "../shop.service";

@Component({
  selector: 'app-favorite-shops',
  templateUrl: './favorite-shops.component.html',
  styleUrls: ['./favorite-shops.component.css']
})
export class FavoriteShopsComponent implements OnInit {

  favoriteShops: Array<any>
  constructor(private authService: AuthService, private shopService: ShopService) { }

  ngOnInit() {

     this.shopService.getFavoritesByUser().subscribe(data =>{
       this.favoriteShops = data
     })



  }

  addToFavorite(shop: any){
    this.favoriteShops.push(shop)
  }

}
