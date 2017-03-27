import {Component} from '@angular/core';
import {Entry} from './domain/entry';
import {EntryService} from "./services/entry.service";

@Component({
  selector: 'app-root',
  templateUrl: './entry.html'
})

export class EntryComponent {
  entry: Entry;

  constructor() {
    this.entry = new Entry();
  }

  onClick(): void {
    console.log("Will save entry: " + this.entry.positiveReviewTxt + ", " + this.entry.goalTxt
        + ", " + this.entry.momentumTxt);
  }


}
