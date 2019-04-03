import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input() component: string;

  constructor(private auth: AuthService, public router: Router) { }

  ngOnInit() {
  }

  signOut(){
    this.auth.signOut();
    this.router.navigate(["login"])
  }

}
