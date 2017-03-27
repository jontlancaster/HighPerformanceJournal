import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Entry} from "../domain/entry";

import 'rxjs/add/operator/toPromise';
import {Journal} from "../domain/journal";

@Injectable()
export class EntryService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private baseUrl = 'http://192.168.0.2:8080/journals/findByJournalId';

  constructor(private http: Http) {}

  getEntries(journalId: number): Promise<Entry[]> {
    return Promise.resolve();
  }

  getJournal(journalId: number): Promise<Journal> {
    const url = '${this.baseUrl}/${journalId}';

    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Journal)
      .catch(this.handleError);
  }

  getEntry(entryId: number): Promise<Entry> {
    const url = '${this.baseUrl}/${entryId}';
    return this.http.get('http://192.168.0.2:8080/journals/findByJournalId/'+entryId)
      .toPromise()
      .then(response => response.json().data as Entry)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("Error occurred", error);
    return Promise.reject(error.message || error);
  }
}
