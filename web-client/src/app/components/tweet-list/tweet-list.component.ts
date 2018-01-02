import { Component, Input, OnInit } from '@angular/core';
import { Tweet } from '../../models/tweet/tweet';

@Component({
  selector: 'ytr-tweet-list',
  templateUrl: './tweet-list.component.html',
  styleUrls: ['./tweet-list.component.scss']
})
export class TweetListComponent implements OnInit {

  @Input() tweets: Tweet[];

  constructor() {
  }

  ngOnInit() {
  }

}
