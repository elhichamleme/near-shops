import {Component, HostListener, OnInit} from '@angular/core';
import { ShopService } from "../shop.service";
import {FavoriteShopsComponent} from "../favorite-shops/favorite-shops.component";

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.css']
})
export class ShopListComponent implements OnInit {
  shops: Array<any>;

  constructor(private shopService: ShopService) { }

  ngOnInit() {
    this.shopService.getAll().subscribe(data =>{
      this.shops = data;

    })




  }

 // @HostListener('window:scroll',['$event'])
  moreShops(event){


  //  this.shops=this.shops.concat(this.shops)


  }

   data=  []

  likeShop(shop: any)
  {
   // this.favoriteShopsComponent.addToFavorite(shop)

  }

  dislikeShop(shop: any)
  {

  }
}

