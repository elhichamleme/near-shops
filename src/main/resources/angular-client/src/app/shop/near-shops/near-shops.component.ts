import {Component, OnInit} from '@angular/core';
import {ShopService} from "../shop.service";

@Component({
  selector: 'app-shop-list',
  templateUrl: './near-shops.component.html',
  styleUrls: ['./near-shops.component.css']
})
export class NearShopsComponent implements OnInit {
  shopsArray: Array<any>;

  constructor(private shopService: ShopService) { }

  ngOnInit() {
    console.log("get gps coords");
    if(navigator.geolocation){
      console.log("geolocation exist");
      navigator.geolocation.getCurrentPosition(position => {
        this.shopService.nearShops(position.coords.latitude, position.coords.longitude).subscribe(data =>{
          this.shopsArray = data;

        })
      })

    }







  }

  likeShop(shopId: any)
  {
    this.shopService.likeShop(shopId).subscribe(data =>{});
   console.log(shopId)

  }

  dislikeShop(shopId: any)
  {
    this.shopService.dislikeShop(shopId).subscribe(data =>{});
    this.shopsArray = this.shopsArray.filter(value => {
      return value["id"] != shopId
    })
    console.log(shopId)
  }
}

