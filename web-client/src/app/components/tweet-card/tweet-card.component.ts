import { Component, Input, OnInit } from '@angular/core';
import { Tweet } from '../../models/tweet/tweet';

@Component({
  selector: 'ytr-tweet-card',
  templateUrl: './tweet-card.component.html',
  styleUrls: ['./tweet-card.component.scss']
})
export class TweetCardComponent implements OnInit {

  @Input() tweet: Tweet;

  constructor() { }

  ngOnInit() {
  }
}
