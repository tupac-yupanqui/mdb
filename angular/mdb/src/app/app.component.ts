import { Component, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'mdb-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(@Inject(DOCUMENT) private document: Document) {}
  toggleTheme() {
    this.document.body.classList.toggle("dark-theme");
  }
  title = 'Musikarchiv';
}
