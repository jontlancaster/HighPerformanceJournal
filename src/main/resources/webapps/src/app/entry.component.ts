import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';

import {JournalEntry} from './domain/journalEntry';
import {MainService} from "./services/main.service";

@Component({
  selector: 'app-root',
  templateUrl: './entry.html'
})

export class EntryComponent implements OnInit {
  entry: JournalEntry;
  journalId: number;

  constructor(private entryService: MainService,
              private route: ActivatedRoute,
              private location: Location) {
    this.entry = new JournalEntry();
  }

  onSave(): void {
    console.log("Will save entry in JournalId: " + this.journalId);
    //Todo: entry needs to have a Journal then the journal have its id set
    this.entry.journal = this.journalId;
    this.entryService.saveEntry(this.entry)
      .then(() => this.goBack());
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.journalId = +params['journalId'];
    });
  }

  goBack(): void {
    this.location.back();
  }
}
