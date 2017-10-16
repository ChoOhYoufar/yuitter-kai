import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ytr-tweet-form',
  templateUrl: './tweet-form.component.html',
  styleUrls: ['./tweet-form.component.scss']
})
export class TweetFormComponent implements OnInit {

  @Input() tweetFormContainer;

  constructor() { }

  ngOnInit() {
  }

  submit() {

  }

}
