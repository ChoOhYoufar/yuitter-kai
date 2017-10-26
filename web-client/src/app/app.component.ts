import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { TweetHttpService } from "./repository/http/tweet-http.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor() {}
}
