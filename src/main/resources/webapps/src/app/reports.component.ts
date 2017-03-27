import 'rxjs/add/operator/switchMap'
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';

import {Entry} from './domain/entry';
import {Journal} from './domain/journal';
import {EntryService} from './services/entry.service';

@Component({
  selector: 'app-root',
  templateUrl: 'reports.html'
})

export class ReportsComponent implements OnInit {

  entryList: Entry[];
  entriesListSize: number;
  journal: Journal;
  selectedEntry: Entry;

  constructor(private entryService: EntryService,
              private route: ActivatedRoute) {
    this.entriesListSize = 0;
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) =>
        this.entryService.getJournal(1))
      .subscribe(journal => this.journal = journal);

  }


  onSelectEntry(selectedEntry: Entry): void {
    this.selectedEntry = selectedEntry;
  }
}
