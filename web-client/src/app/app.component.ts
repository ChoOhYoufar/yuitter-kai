import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { TweetHttpService } from "./repository/http/tweet-http.service";
import { MatDialog } from '@angular/material';
import { SignInFormComponent } from './components/sign-in-form/sign-in-form/sign-in-form.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    // this.openSignInForm();
  }

  openSignInForm() {
    let dialogRef = this.dialog.open(SignInFormComponent, {
      width: '250px'
    });

    dialogRef.afterClosed().subscribe(result => result);
  }
}
