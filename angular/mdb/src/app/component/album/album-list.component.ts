import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'
import { Album } from 'src/app/data/interfaces';
import { AlbumFilter } from 'src/app/data/data';
import { RestService } from '../../service/rest.service';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from '../../dialog/message-dialog/message-dialog.component';

import { HostListener } from "@angular/core";

import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';

import { ElementRef, AfterViewInit, ViewChild } from '@angular/core';
import {fromEvent } from 'rxjs';
import { debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';

const cardWidth = 250;
const cardSpacing = 20;

@Component({
  selector: 'mdb-album',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.scss']
})
export class AlbumListComponent implements OnInit {
  
  @ViewChild('albumSearch') albumSearch: ElementRef;
  @ViewChild('artistSearch') artistSearch: ElementRef;
  @ViewChild('yearSearch') yearSearch: ElementRef;

  scrHeight:any;
  scrWidth:any;
  albums : Album[];
  counta : number;
  itemsPerPageSelection : number[] = []
 
  filterForm: FormGroup;
  _filter: AlbumFilter = {
    album: '',
    artist: '',
    year: '',
    sorttype: 'ARTIST',
    sortorder: 'ASC',
    start: 0,
    amount: 10
  }
  filterShown: boolean = true;
  paginatorShown: boolean = true;

  constructor(
    private router: Router,
    private rest:  RestService,
    private dialog: MatDialog) { 
      this.getScreenSize();

      this.setItemsPerPage()
      this.filterForm = new FormGroup({
        album: new FormControl(''),
        artist: new FormControl(''),        
        year: new FormControl(''),
        sorttype: new FormControl('ARTIST'),        
        sortorder: new FormControl('ASC')        
      })
   
     }

    @HostListener('window:resize', ['$event'])
    getScreenSize() {
          this.scrHeight = window.innerHeight;
          this.scrWidth = window.innerWidth;
    }

  setItemsPerPage() {
    let colCount = this.getColCount()
    this.itemsPerPageSelection=[]
    this.itemsPerPageSelection.push(colCount)
    this.itemsPerPageSelection.push(colCount*2)
    this.itemsPerPageSelection.push(colCount*5)
    this.itemsPerPageSelection.push(colCount*10)
    this.itemsPerPageSelection.push(100)
    this._filter.amount=this.itemsPerPageSelection[1]
  }  

  ngOnInit(): void {
    this.load();
  }

  ngAfterViewInit() {
    // server-side search
    fromEvent(this.albumSearch.nativeElement,'keyup')
      .pipe(
          debounceTime(500),
          distinctUntilChanged(),
          tap((text) => {
            this._filter.album = this.albumSearch.nativeElement.value
            this.load()
          })
    )
    .subscribe();
    fromEvent(this.artistSearch.nativeElement,'keyup')
      .pipe(
          debounceTime(500),
          distinctUntilChanged(),
          tap((text) => {
            this._filter.artist = this.artistSearch.nativeElement.value
            this.load()
          })
    )
    .subscribe();
    fromEvent(this.yearSearch.nativeElement,'keyup')
      .pipe(
          debounceTime(500),
          distinctUntilChanged(),
          tap((text) => {
            this._filter.year = this.yearSearch.nativeElement.value
            this.load()
          })
    )
    .subscribe();
}

  load(): void {

    this.rest.getCountAlbums(this._filter).subscribe(
      (data: number) => {
        this.counta = data;
      }
    );

    this.rest.getAlbums(this._filter).subscribe(
      (data: Album[])=>{
        this.albums = data;
        this.paginatorShown = (this.counta>this._filter.amount)
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

  onChanges(): void {
    this.filterForm.valueChanges.subscribe(val => {
      console.log(val);
      console.log(this._filter)
      Object.assign(this._filter, val)
      console.log(this._filter)
      this.ngOnInit()
    })
  }

  getControlPanelWidth() {
    this.setItemsPerPage()
    let colcount = this.getColCount()
    return (colcount*(cardWidth+cardSpacing)-cardSpacing)+'px'
  }

  getCardWidth() {
    return cardWidth
  }

  getColCount() {
    return Math.floor((this.scrWidth-65)/(cardWidth+cardSpacing))
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
      console.log(id)
      this.router.navigate(['album','view',id])
    }

    toggleFilterPanel() {
      this.filterShown = !this.filterShown
    }

    page(pageEvent:PageEvent) {
      this._filter.amount=pageEvent.pageSize
      this._filter.start=pageEvent.pageIndex*this._filter.amount
      this.load()
    }

    onSortorderChange(value : string) {
      this._filter.sortorder = value
      this.load()
    }
    onSorttypeChange(value : string) {
      this._filter.sorttype = value
      this.load()
    }
}
