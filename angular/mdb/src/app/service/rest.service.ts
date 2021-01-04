import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { URLSearchParams } from 'url';


@Injectable({
  providedIn: 'root'
})
export class RestService {
  private REST_API_SERVER = "http://localhost:8080/artist"; 
  private REST_API_SERVER2 = "http://localhost:8080/api/auth/signin"; 
  private REST_API_SERVER3 = "http://localhost:8080/api/test/all"; 
  private REST_API_SERVER4 = "http://localhost:8080/api/test/user"; 
  private REST_API_GET_ALBUMS = "http://localhost:8080/albums"; 

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

  getAlbums() {
    return this.http.get<any>(this.REST_API_GET_ALBUMS, this.getHeaders('{"year":2020, "sort":"ARTIST","order":"ASC"}'));
  }

  signin(username: string, password: string) {
    return this.http.post<any>(this.REST_API_SERVER2, {'username':username, 'password':password}).toPromise();
  }


  private getHeaders(params?: string) {
    return {'headers': new HttpHeaders().set('Authorization', 'Bearer '+this.token), 
            'params' : new HttpParams().set('filter', params)};
  }
}
