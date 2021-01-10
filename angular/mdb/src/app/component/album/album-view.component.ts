import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";

@Component({
  selector: 'mdb-album-view',
  templateUrl: './album-view.component.html',
  styleUrls: ['./album-view.component.scss']
})
export class AlbumViewComponent implements OnInit {

  subscription: Subscription;

  albumId: number;

  constructor(private route: ActivatedRoute) { 

  }

  ngOnInit(): void {
    this.subscription = this.route.params.subscribe(params => {
      const id = params['id'] || '';
      console.log(id)
      this.albumId = id;
    })
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
