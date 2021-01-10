import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TimelineComponent } from './timeline/timeline.component';
import { SettingsComponent } from './settings/settings.component';
import { LoginComponent } from './component/login/login.component';
import { LogoutComponent } from './component/logout/logout.component';
import { albumRoutes, albumRoutingComponents } from './component/album/album.routes'

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'timeline', component: TimelineComponent },
  { path: 'settings', component: SettingsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'album', children: albumRoutes }  
//  { path: '', redirectTo: 'timeline', pathMatch: 'full'},
];

export const  routingComponents = [
  SettingsComponent,
  TimelineComponent,
  HomeComponent,
  LoginComponent,
  LogoutComponent,
  ...albumRoutingComponents
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
