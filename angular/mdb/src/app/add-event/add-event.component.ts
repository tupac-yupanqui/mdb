import { Component, OnInit } from '@angular/core';
import { BabywatchService, timelineEventTypes, TimelineEventType } from '../service/babywatch.service';
import { MatBottomSheetRef } from '@angular/material/bottom-sheet';

@Component({
  selector: 'mdb-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit {

  constructor(private babyService: BabywatchService, private bottomSheetRef: MatBottomSheetRef<AddEventComponent>) { 
  }

  get eventTypes() { return timelineEventTypes; }
  get babyName() { return this.babyService.babyName || 'Das Baby'; }

  addEvent(data: { eventType: TimelineEventType, comment: string}) {
    this.babyService.addTimelineEvent( {
      date: new Date(),
      eventType: data.eventType,
      comment: data.comment
    })
    this.bottomSheetRef.dismiss();
  }

  ngOnInit(): void {
  }

}
