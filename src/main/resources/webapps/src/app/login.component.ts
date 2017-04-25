import {Component} from '@angular/core';
import {User} from './domain/user';
import {MainService} from "./services/main.service";
import {isNullOrUndefined} from "util";
import {Router} from "@angular/router";
import {Journal} from "./domain/journal";
import {JournalEntry} from "./domain/journalEntry";
import {Observable} from "rxjs";
import {error} from "util";

@Component({
  selector: 'app-root',
  templateUrl: './login.html'
})

export class LoginComponent {
  private user: User;
  private newUser: boolean;
  private journal: Journal;

  constructor(private entryService: MainService, private router: Router) {
    this.user = new User();
    this.newUser = false;
  }

  onSignUp(): void {
    this.newUser = true;
  }

  onSignIn(): void {
    console.log("attempting to login with " + this.user.username);
    // //call backend, if journal returned than authenticated else
    // let result: Observable<Journal> = this.entryService.authenticate(this.user);
    // result.subscribe(journal => {this.journal = journal},
    // error => {
    //   console.error("There was an error while authenticating, error: ", error)
    // });
    // let dialogBox;
    // //display login failed and have them try again
    // if (isNullOrUndefined(journal)) {
    //   dialogBox = document.getElementById("tryAgainDialog");
    //   dialogBox.show();
    //   return;
    // } else {
    //   let journal: JournalEntry = journalEntry;
    //   this.router.navigate(['/entry', journalEntry.journalId])
    // }


    //if success then send to Journal and hide login on menu

  }

  createNewUser(): void {
    let userCreated = this.entryService.createUser(this.user);
    console.log("will create username " + this.user.username);
    this.newUser = false;
  }
}
