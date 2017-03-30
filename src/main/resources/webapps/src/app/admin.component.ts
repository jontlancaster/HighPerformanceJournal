import {Component} from '@angular/core';
import {User} from "./domain/user";
import {MainService} from "./services/main.service";

@Component({
  selector: 'app-root',
  templateUrl: 'admin.html'
})

export class AdminComponent {
  private selectedUser: User;
  private username: string;

  constructor(private entryService: MainService) {
  }

  editUser(): void {

  }

  viewUserJournals(): void {

  }
}
