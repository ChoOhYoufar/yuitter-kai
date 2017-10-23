import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { TweetHttpService } from "./repository/http/tweet-http.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  constructor(private tweetHttpService: TweetHttpService) {}

  ngOnInit() {
    this.tweetHttpService.fetchStream().subscribe();
    //
    // this.http.get('api/tweets').subscribe(
    //   response => {
    //     console.log(response.json())
    //   }
    // );
  }
}
