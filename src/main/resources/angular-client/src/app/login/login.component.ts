import {Component} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {
   isRegistration = false;

  constructor(private authService: AuthService, public router: Router) { }

  signIn(email: string, password: string){
    this.authService.signIn(email, password).subscribe(data => {
      localStorage.setItem("user", JSON.stringify(data))
      this.router.navigate(["shops"])

    });

  }

  createUser(email: string, password: string){
    this.authService.createUser(email, password).subscribe(data =>{
      this.isRegistration = false;
      this.router.navigate(["login"]);
    })
  }

}
