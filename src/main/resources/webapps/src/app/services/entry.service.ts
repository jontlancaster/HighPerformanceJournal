import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {JournalEntry} from "../domain/entry";

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map'
import {Journal} from "../domain/journal";
import {Observable} from "rxjs";
import {User} from "../domain/user";

@Injectable()
export class EntryService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private baseUrl = 'http://localhost:8080/';

  constructor(private http: Http) {
  }

  getEntries(journalId: number): Promise<JournalEntry[]> {
    return Promise.resolve();
  }

  getJournal(journalId: number): Promise<Journal> {
    return this.http.get(this.baseUrl + "journals/findByJournalId/" + journalId)
      .toPromise()
      .then(response => response.json().data as Journal)
      .catch(this.handleError);
  }

  getJournalsForUserId(userId: number): Observable<Journal[]> {
    return this.http.get(this.baseUrl + "journals/findbyuserid/" + userId)
      .map((response:Response) => response.json())
      .catch(this.handleError)
  }

  private handleError(error: any): Observable<any> {
    console.error("Error occurred on EntryService:::", error);
    return Observable.throw(error.json().error || 'Server error');
  }

  saveEntry(entry: JournalEntry): Promise<JournalEntry> {
    let saveUrl = this.baseUrl + "journalEntries/create";
    return this.http.post(saveUrl, JSON.stringify(entry), {headers: this.headers})
      .toPromise()
      .then((savedEntry:Response) => savedEntry.json())
      .catch(this.handleError)
  }

  createUser(user: User): Promise<User> {
    let createUserUrl = this.baseUrl + "users/createWithDefaultRole";
    return this.http.post(createUserUrl, JSON.stringify(user), {headers: this.headers})
      .toPromise()
      .then(user => user.json().data as User)
      .catch(this.handleError)
  }
}
