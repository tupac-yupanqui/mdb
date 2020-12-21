import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TimelineComponent } from './timeline/timeline.component';
import { SettingsComponent } from './settings/settings.component';
import { LoginComponent } from './component/login/login.component';
import { LogoutComponent } from './component/logout/logout.component';

const routes: Routes = [
  { path: 'timeline', component: TimelineComponent },
  { path: 'settings', component: SettingsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '', redirectTo: 'timeline', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
