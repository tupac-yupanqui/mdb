import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { URLSearchParams } from 'url';
import { AlbumFilter } from '../data/data';


@Injectable({
  providedIn: 'root'
})
export class RestService {
  private REST_API_SERVER = "http://localhost:8080/artist"; 
  private REST_API_SERVER3 = "http://localhost:8080/api/test/all"; 
  private REST_API_SERVER4 = "http://localhost:8080/api/test/user"; 
  private REST_API_LOGIN = "http://localhost:8080/api/auth/signin"; 
  private REST_API_GET_ALBUMS = "http://localhost:8080/albums"; 
  private REST_API_GET_COUNT_ALBUMS = "http://localhost:8080/counta"; 
  private REST_API_GET_ALBUM_DETAILS = "http://localhost:8080/album/view"; 
  
  token: string;

  constructor(
    private http : HttpClient
    ) { }

  setToken(t: string) {
    this.token = t;
  }

  getArtist() {
    return this.http.get<any>(this.REST_API_SERVER, this.getHeaders());
  }

  getAlbums(filter: AlbumFilter) {
    return this.http.get<any>(this.REST_API_GET_ALBUMS, this.getHeaders(this.getFilterParam(JSON.stringify(filter))));
  }

  getCountAlbums(filter: AlbumFilter) {
    return this.http.get<number>(this.REST_API_GET_COUNT_ALBUMS, this.getHeaders(this.getFilterParam(JSON.stringify(filter))))
  }

  getAlbumDetails(id: number) {
    return this.http.get<any>(this.REST_API_GET_ALBUM_DETAILS, this.getHeaders(new HttpParams().set('id', id.toString())))
  }

  signin(username: string, password: string) {
    return this.http.post<any>(this.REST_API_LOGIN, {'username':username, 'password':password}).toPromise();
  }


  private getHeaders(params?: HttpParams) {
    return {'headers': new HttpHeaders().set('Authorization', 'Bearer '+this.token), 
            'params' : params};
  }
  private getFilterParam(param?: string) {
    return new HttpParams().set('filter', param)
  }
}
