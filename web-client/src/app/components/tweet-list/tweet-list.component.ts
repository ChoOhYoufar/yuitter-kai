import { Component, Input, OnInit } from '@angular/core';
import { TweetList } from '../../models/tweet/tweet-list';

@Component({
  selector: 'ytr-tweet-list',
  templateUrl: './tweet-list.component.html',
  styleUrls: ['./tweet-list.component.scss']
})
export class TweetListComponent implements OnInit {

  @Input() tweetList: TweetList;
  @Input() str: string;

  constructor() {
  }

  ngOnInit() {
  }

}
