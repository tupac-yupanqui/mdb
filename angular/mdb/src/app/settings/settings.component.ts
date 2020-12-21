import { Component, OnInit } from '@angular/core';
import { BabywatchService } from '../service/babywatch.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { DeleteTimelineDialogComponent } from '../delete-timeline-dialog/delete-timeline-dialog.component';

@Component({
  selector: 'mdb-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})

export class SettingsComponent implements OnInit {

  constructor(
    private babyService: BabywatchService,
    private snackbar: MatSnackBar,
    private dialog: MatDialog
    ) { 
      console.log("CONS "+snackbar);

    }

  get babyName() { return this.babyService.babyName; }

  get babyNameWithFallback() { return this.babyService.babyName || 'das Baby'; }

  saveBabyName(babyName: string) {
    this.babyService.babyName = babyName.trim();
    this.snackbar.open('Der Babyname wurde gespeichert','', {duration:2000});
  }

  ngOnInit(): void {
  }

  clearTimeline() {
    const dialogRef = this.dialog.open(DeleteTimelineDialogComponent, {
      width: "80%",
      maxWidth: "450px",
      data: this.babyNameWithFallback
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.babyService.clearTimeline();
      }
    });
  }

}
