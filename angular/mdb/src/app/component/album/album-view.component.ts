import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";
import { RestService } from '../../service/rest.service';
import { AlbumDetails } from 'src/app/data/interfaces';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from '../../dialog/message-dialog/message-dialog.component';
import {sprintf} from 'sprintf-js';

@Component({
  selector: 'mdb-album-view',
  templateUrl: './album-view.component.html',
  styleUrls: ['./album-view.component.scss']
})
export class AlbumViewComponent implements OnInit {
  displayedColumns: string[] = ['artist','name'];
  subscription: Subscription;
  albumId: number;
  album : AlbumDetails;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private rest:  RestService,
    private dialog: MatDialog) { 

  }

  ngOnInit(): void {
    this.subscription = this.route.params.subscribe(params => {
      const id = params['id'] || '';
      console.log(id)
      this.albumId = id;
    })
    this.load();
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  load(): void {

    this.rest.getAlbumDetails(this.albumId).subscribe(
      (data: AlbumDetails)=>{
        this.album = data;
        console.log(data)
      },
      (error)=>{
        console.log(error.error.error);
        console.log('is a error '+JSON.stringify(error))
        const dialogRef = this.dialog.open(
          MessageDialogComponent, 
          {width: '80%', maxWidth: '450px', data: {'title':'Login fehlgeschlagen','text':error.code+' - '+error.text}});
        dialogRef.afterClosed().subscribe(result => {console.log('Closed')});
      })
  }

  edit(id: number) {
    this.router.navigate(['album','edit',this.albumId])
  }


  getSubalbumTitle(k: string) {
    if (k==='') return 'Titelliste'
    return k
  }
  getFormatedLength(n: number) {
    return sprintf("%d'%02d",n/60,n%60)
  }
}
