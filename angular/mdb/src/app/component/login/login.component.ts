import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from '../../dialog/message-dialog/message-dialog.component';


@Component({
  selector: 'mdb-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


export class LoginComponent implements OnInit {
  
  creds : any;

  constructor(
    public userService: UserService,
    private router: Router,
    private dialog: MatDialog
  ) { 
    this.creds = {
      name: '',
      password: ''
    }
    console.log('contructor')
  }

  ngOnInit(): void {
    console.log('init')
  }

  async login(form: any) {
      
    try {
      const result = await this.userService.authenticate(form.name, form.password);
      console.log(JSON.stringify(result))
      this.router.navigate([''])
    } catch (error) {
      const dialogRef = this.dialog.open(
        MessageDialogComponent, 
        {width: '80%', maxWidth: '450px', data: {'title':'Login fehlgeschlagen','text':error.code+' - '+error.text}});
      dialogRef.afterClosed().subscribe(result => {console.log('Closed')});
    }
  }

}
