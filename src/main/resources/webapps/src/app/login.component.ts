import {Component} from '@angular/core';
import {User} from './domain/user';
import {MainService} from "./services/main.service";

@Component({
  selector: 'app-root',
  templateUrl: './login.html'
})

export class LoginComponent {
  user: User;
  newUser: boolean;

  constructor(private entryService: MainService) {
    this.user = new User();
    this.newUser = false;
  }

  onSignUp(): void {
    this.newUser = true;
  }

  onSignIn(): void {
    console.log("attempting to login with " + this.user.username);
  }

  createNewUser(): void {
    let userCreated = this.entryService.createUser(this.user);
    console.log("will create username " + this.user.username);
    this.newUser = false;
  }
}
