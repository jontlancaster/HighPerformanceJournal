import {Component, OnInit} from '@angular/core';
import {User} from "./domain/user";

@Component({
    selector: 'app-root',
    templateUrl: 'admin.html'
})

export class AdminComponent extends OnInit{
    private searchedUser: string;
    private username: string;
    private isAdmin: boolean;
    private newPassword: string;
    private confirmPassword: string;
    private firstName: string;
    private lastName: string;

    ngOnInit(): void {
        this.isAdmin = true;
        this.searchedUser = 'Harcoded username'
    }

    updatePassword(): void {

    }

    updateName(): void {

    }

    searchUser(): void {

    }

    viewUserJournals(): void {

    }

    updateRole(): void {

    }
}
