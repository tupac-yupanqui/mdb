import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Album } from 'src/app/data/interfaces';
import { RestService } from '../../service/rest.service';
import { MatDialog } from '@angular/material/dialog';

import { MessageDialogComponent } from '../../dialog/message-dialog/message-dialog.component';

import { HostListener } from "@angular/core";

const cardWidth = 250;
const cardSpacing = 20;

@Component({
  selector: 'mdb-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.scss']
})
export class AlbumComponent implements OnInit {
  
  scrHeight:any;
  scrWidth:any;

  albums : Album[];

  constructor(
    private router: Router,
    private rest:  RestService,
    private dialog: MatDialog) { 
      this.getScreenSize();
    }

    @HostListener('window:resize', ['$event'])
    getScreenSize(event?) {
          this.scrHeight = window.innerHeight;
          this.scrWidth = window.innerWidth;
         // console.log(this.scrHeight, this.scrWidth);
    }

  ngOnInit(): void {
    this.rest.getAlbums().subscribe(
      (data: Album[])=>{
        this.albums = data;
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

  getControlPanelWidth() {
    let colcount = Math.floor((this.scrWidth-65)/(cardWidth+cardSpacing))
    return (colcount*(cardWidth+cardSpacing)-cardSpacing)+'px'
  }

  getCardWidth() {
    return cardWidth
  }

  getCover(album:Album) : string {
    if (!album) return '' 
    if (album.coversmall) {
      return 'small\\'+album.coversmall
    }
    if (album.cover) {
      return 'cover\\'+album.cover
    }
    return 'placeholder.png'
  }

    details(id: number) {
      this.router.navigate(['timeline'])
    }
}
