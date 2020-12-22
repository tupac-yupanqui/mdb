import { Injectable }    from '@angular/core';
import {CanActivate,
        Router,
        ActivatedRouteSnapshot,
        RouterStateSnapshot } from '@angular/router';
import { Observable }    from 'rxjs';
import { AuthenticationService } from '../../service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

  constructor(private loginService: AuthenticationService,
              private router: Router) {
  }

  canActivate(routeSnapshot: ActivatedRouteSnapshot,
              routerSnapshot: RouterStateSnapshot): Observable<boolean> | boolean {

    if (!this.loginService.isUserLoggedIn()) {
      const redirect = encodeURI(routerSnapshot.url);
      this.router.navigate(['/login'], {queryParams: {redirect: redirect}});
    }
    return true;
  }
}
