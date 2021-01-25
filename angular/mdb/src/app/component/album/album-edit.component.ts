import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";
import { RestService } from '../../service/rest.service';
import { AlbumDetails, Artist } from 'src/app/data/interfaces';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from '../../dialog/message-dialog/message-dialog.component';
import { FormControl, FormBuilder, FormGroup, FormArray  } from '@angular/forms';
import {Observable} from 'rxjs';
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

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private rest:  RestService,
    private dialog: MatDialog,
    private fb: FormBuilder) { 

      this.albumForm = new FormGroup({
        album: new FormGroup({
          artist: new FormControl(this.defart),
          name: new FormControl('TT'),
          release: new FormControl()
        })
      });
  }


  ngOnInit(): void {
    this.subscription = this.route.params.subscribe(params => {
      const id = params['id'] || '';
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

        this.albumForm.patchValue(this.album)
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
          console.log('LOAD')
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
