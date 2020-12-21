import { Component, OnInit } from '@angular/core';

import { BabywatchService } from '../babywatch.service';
import { AddEventComponent } from '../add-event/add-event.component';
import { MatBottomSheet } from '@angular/material/bottom-sheet';

@Component({
  selector: 'mdb-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {

  constructor(
    private babyService: BabywatchService, 
    private bottomSheet: MatBottomSheet
  ) { }

  get timeline() { return this.babyService.timeline; }
  get babyname() { return this.babyService.babyName || 'Das Baby'; }

  ngOnInit() {
  }

  addEvent() {
    console.log("ADD");
    this.bottomSheet.open(AddEventComponent);
  //  this.babyService.addRandomTimelineEvent();
  }

}
