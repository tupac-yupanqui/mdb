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

import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { LOCALE_ID } from '@angular/core';
import de from '@angular/common/locales/de';
import { registerLocaleData } from '@angular/common';

import { HttpClientModule } from "@angular/common/http";
import { DomSanitizer } from "@angular/platform-browser";
import { MatIconRegistry } from "@angular/material/icon";
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { FormsModule } from '@angular/forms';

import { MatDialogModule } from '@angular/material/dialog';
import { DeleteTimelineDialogComponent } from './delete-timeline-dialog/delete-timeline-dialog.component';

registerLocaleData(de);

@NgModule({
  declarations: [
    AppComponent,
    TimelineComponent,
    SettingsComponent,
    AddEventComponent,
    DeleteTimelineDialogComponent
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
    MatCardModule,
    MatBottomSheetModule,
    FormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatDialogModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: "de-de"}
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    AddEventComponent,
    DeleteTimelineDialogComponent
  ]
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