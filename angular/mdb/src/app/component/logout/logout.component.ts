import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../service/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'mdb-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.authenticationService.logOut();
    this.router.navigate(['login']);
 }

}
