import { Injectable } from '@angular/core';
import { TweetHttpService } from '../../repositories/http/tweet/tweet-http.service';
import { Observable } from 'rxjs/Observable';
import { Timeline } from '../../models/timeline/timeline';

@Injectable()
export class TimelineService {

  constructor(
    private tweetHttpService: TweetHttpService
  ) {}

  fetchTimeline(): Observable<Timeline> {
    return this.tweetHttpService.getStream();
  }
}
