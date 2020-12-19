import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { TimelineComponent } from './timeline/timeline.component';
import { SettingsComponent } from './settings/settings.component';
import { AddEventComponent } from './add-event/add-event.component';

import { LOCALE_ID } from '@angular/core';
import de from '@angular/common/locales/de';
import { registerLocaleData } from '@angular/common';

import { HttpClientModule } from "@angular/common/http";
import { DomSanitizer } from "@angular/platform-browser";
import { MatIconRegistry } from "@angular/material/icon";


registerLocaleData(de);

@NgModule({
  declarations: [
    AppComponent,
    TimelineComponent,
    SettingsComponent,
    AddEventComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: "de-de"}
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddEventComponent]
})
export class AppModule {
  constructor(
    private iconRegistry: MatIconRegistry,
    private sanitizer: DomSanitizer
  ) {
    this.iconRegistry.addSvgIcon(
      "feed",
      this.sanitizer.bypassSecurityTrustResourceUrl("../assets/feed.svg")
    );
    this.iconRegistry.addSvgIcon(
      "bath",
      this.sanitizer.bypassSecurityTrustResourceUrl("../assets/bath.svg")
    );
    this.iconRegistry.addSvgIcon(
      "diaper",
      this.sanitizer.bypassSecurityTrustResourceUrl("../assets/diaper.svg")
    );
    this.iconRegistry.addSvgIcon(
      "sleep",
      this.sanitizer.bypassSecurityTrustResourceUrl("../assets/sleep.svg")
    );
  }
}