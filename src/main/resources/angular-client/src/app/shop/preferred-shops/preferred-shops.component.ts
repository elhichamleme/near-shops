import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {ShopService} from "../shop.service";

@Component({
  selector: 'app-favorite-shops',
  templateUrl: './preferred-shops.component.html',
  styleUrls: ['./preferred-shops.component.css']
})
export class PreferredShopsComponent implements OnInit {

  preferredShopsArray: Array<any>
  constructor(private authService: AuthService, private shopService: ShopService) { }

  ngOnInit() {

     this.shopService.preferredShops().subscribe(data =>{
       this.preferredShopsArray = data
     })



  }

  dislikeShop(shop: any){
    this.preferredShopsArray.push(shop)
  }

}
