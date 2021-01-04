import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Album, AlbumFilter } from 'src/app/data/interfaces';
import { RestService } from '../../service/rest.service';
import { MatDialog } from '@angular/material/dialog';

import { MessageDialogComponent } from '../../dialog/message-dialog/message-dialog.component';

import { HostListener } from "@angular/core";

import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';

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

  filterForm: FormGroup;
  filter: AlbumFilter;

  constructor(
    private router: Router,
    private rest:  RestService,
    private dialog: MatDialog) { 
      this.getScreenSize();

      this.filterForm = new FormGroup({
        album: new FormControl(''),
        artist: new FormControl(''),        
        year: new FormControl('')        
      })
    }

    @HostListener('window:resize', ['$event'])
    getScreenSize() {
          this.scrHeight = window.innerHeight;
          this.scrWidth = window.innerWidth;
    }

  ngOnInit(): void {
    let filter = {
      year: '',
      sort: 'ARTIST',
      order: 'ASC'
    }
    this.rest.getAlbums(filter).subscribe(
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
