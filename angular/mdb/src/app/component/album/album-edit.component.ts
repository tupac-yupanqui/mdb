import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";
import { RestService } from '../../service/rest.service';
import { AlbumDetails, Artist } from 'src/app/data/interfaces';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from '../../dialog/message-dialog/message-dialog.component';
import { FormControl, FormBuilder, FormGroup, FormArray  } from '@angular/forms';
import {Observable, of} from 'rxjs';
import { map, startWith } from 'rxjs/operators';

@Component({
  selector: 'mdb-album-edit',
  templateUrl: './album-edit.component.html',
  styleUrls: ['./album-edit.component.scss']
})
export class AlbumEditComponent implements OnInit {

  artistControl = new FormControl();
  subscription: Subscription;
  albumId: number;
  album : AlbumDetails;
  filteredArtists : Observable<Artist[]>;
  artists : Artist[];

  albumForm : FormGroup;

  defart : Artist = {id:0,name:'DEFAULT'}

  titelListArray: FormArray;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private rest:  RestService,
    private dialog: MatDialog,
    private fb: FormBuilder) { 

      this.albumForm = fb.group({
        album: fb.group({
          id: [''],
          artist: [''],
          name: [''],
          release: [''],
          cover: [''],
          coversmall: [''],
          subalbums: fb.array([
            fb.group({
              id: [''],
              name: ['']
            })
          ]),
          titels: fb.array([
            fb.group({
              name: [''],
              list: fb.array([
                fb.group({
                  id: [''],
                  name: [''],
                  version: [''],
                  comment: [''],
                  tracknr: [''],
                  length: [''],
                  artist: ['']
                })
              ])
            })
          ])
        })
      })
      this.titelListArray = this.albumForm.controls.album as FormArray;
  }


  ngOnInit(): void {
    this.subscription = this.route.params.subscribe(params => {
      const id = params['id'] || '';
      this.albumId = id;
    })
    this.load();
    console.log("Load ready")
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  load(): void {

    this.rest.getAlbumDetails(this.albumId).subscribe(
      (data: AlbumDetails)=>{
        this.album = data;
        console.log('LOAD Album')
        console.log(data)
        this.albumForm.patchValue(this.album)
        console.log('AAA '+JSON.stringify(this.albumForm.value))
        //this.filteredArtists = of( this._filterArtists(this.album.album.artist.name))
      },
      (error)=>{
        console.log(error.error.error);
        console.log('is a error '+JSON.stringify(error))
        const dialogRef = this.dialog.open(
          MessageDialogComponent, 
          {width: '80%', maxWidth: '450px', data: {'title':'Login fehlgeschlagen','text':error.code+' - '+error.text}});
        dialogRef.afterClosed().subscribe(result => {console.log('Closed')});
      })

      this.rest.getArtists().subscribe(
        (data: Artist[])=>{
          this.artists = data;
          console.log('LOAD Artists')
          this.filteredArtists = this.albumForm.get('album').get('artist').valueChanges.pipe(
            startWith(''), map(artist => artist ? this._filterArtists(artist) : this.artists.slice())
          );
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

  view(id: number) {
    console.log('BACK')
    this.router.navigate(['album','view',this.albumId])
  }

  saveAlbum(album: any) {
    console.log('SAVE '+album.album.artist.id)
    console.log('SAVE '+album.album.artist.name)
    console.log('SAVE '+album.album.artist)
    console.log('SAVE '+album.album.name)
  }

  displayArtist(value) {
    if (value) {
      return value.name
    }
  }

  private _filterArtists(value: string) : Artist[] {
    const filterValue = value.toString().toLowerCase();
    console.log("filter "+filterValue)
    return this.artists.filter(artist => artist.name.toLowerCase().includes(filterValue));
  }
}
