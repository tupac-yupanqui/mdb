import { Component, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { UserService } from './service/user.service';

@Component({
  selector: 'mdb-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(
    public userService: UserService, 
    @Inject(DOCUMENT) private document: Document
    ) {}

  toggleTheme() {
    this.document.body.classList.toggle("dark-theme");
  }

ngAfterViewInit() {
}

  title = 'Musikarchiv';
}
