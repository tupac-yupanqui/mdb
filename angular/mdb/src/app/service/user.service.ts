import { Injectable } from '@angular/core';

import { Artist } from '../data/interfaces';
import { UserToken } from '../data/interfaces';
import { MdbError } from '../data/data';

import { RestService } from './rest.service'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  loggedIn = false;

  constructor(private rest: RestService) { }


  async authenticate(username: string, password: string) : Promise<UserToken> {
    console.log('Login '+username+" - "+password);

    const asyncResult = this.rest.signin(username,password);
    await asyncResult.then(
      (answer:UserToken) => {
        this.loggedIn = true;
        this.rest.setToken(answer.token)
        sessionStorage.setItem('token', JSON.stringify(answer))
      }, 
      (fehler:any) => {
        throw new MdbError(fehler.error.error, fehler.status)
      }
      );
    return asyncResult;
  }

  isError() {
    return this.loggedIn==false
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('token')
    return !(user === null)
  }

  logOut() {
    console.log('logout')
    this.loggedIn = false;
    sessionStorage.removeItem('token')
  }
}

