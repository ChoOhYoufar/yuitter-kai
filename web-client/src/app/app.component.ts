import { Component, OnInit, VERSION } from '@angular/core';
import { MatDialog } from '@angular/material';
import { SignInFormComponent } from './components/sign-in-form/sign-in-form.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(
    public dialog: MatDialog
  ) {
    console.log(VERSION);
    this.openSignInForm();
  }

  ngOnInit() {
  }

  openSignInForm() {
    let dialog = this.dialog.open(SignInFormComponent, {
      disableClose: true,
      width: '500px',
      position: {
        top: '15%'
      }
    });
    dialog.afterClosed().subscribe(result => {
      if (result != null) {
      }
    });
  }
}
