import {Component, OnInit} from '@angular/core';
import {ShopService} from "../shop.service";

@Component({
  selector: 'app-shop-list',
  templateUrl: './near-shops.component.html',
  styleUrls: ['./near-shops.component.css']
})
export class NearShopsComponent implements OnInit {
  shops: Array<any>;

  constructor(private shopService: ShopService) { }

  ngOnInit() {
    console.log("get gps coords");
    if(navigator.geolocation){
      console.log("geolocation exist");
      navigator.geolocation.getCurrentPosition(position => {
        this.shopService.nearShops(position.coords.latitude, position.coords.longitude).subscribe(data =>{
          this.shops = data;

        })
      })

    }





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

