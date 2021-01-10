import { Routes } from '@angular/router';
import { AlbumComponent } from "./album.component";
import { AlbumListComponent } from "./album-list.component";
import { AlbumViewComponent } from "./album-view.component";
import { AlbumEditComponent } from "./album-edit.component";

export const albumRoutes: Routes = [{
    path: '',
    children: [
        {path: '', component: AlbumListComponent},
        {path: 'edit/:id', component: AlbumEditComponent},
        {path: 'new', component: AlbumEditComponent},
        {path: 'view/:id', component: AlbumViewComponent}
    ]
}];

export const albumRoutingComponents = [
    AlbumComponent,
    AlbumListComponent,
    AlbumViewComponent,
    AlbumEditComponent
]