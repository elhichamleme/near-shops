import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {ShopService} from "../shop.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-favorite-shops',
  templateUrl: './preferred-shops.component.html',
  styleUrls: ['./preferred-shops.component.css']
})
export class PreferredShopsComponent implements OnInit {

  preferredShopsArray: Array<any>
  constructor(private authService: AuthService, private shopService: ShopService, public router: Router) { }

  ngOnInit() {

     this.shopService.preferredShops().subscribe(data =>{
       this.preferredShopsArray = data
     })



  }

  removeFromPreferred(shopId: any){
    this.shopService.removeFromPreferred(shopId).subscribe(data =>{
      this.preferredShopsArray= this.preferredShopsArray.filter(value => {
        return value["id"] != shopId
      })
    });

  }

}
