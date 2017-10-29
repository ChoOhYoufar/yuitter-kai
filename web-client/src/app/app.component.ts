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
    this.openSignInForm();
  }

  openSignInForm() {
    let dialog = this.dialog.open(SignInFormComponent, {
      width: '500px'
    });
    dialog.afterClosed().subscribe(result => {
      if (result != null) {
      }
    })
  }
}
