import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stream } from '../../stream/stream';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mapTo';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/toPromise';
import { Timeline } from '../../../models/timeline/timeline';
import { TweetCreate } from '../../../models/tweet/tweet-create';
import { TimelinesHttpService } from '../timeline/timelines-http.service';

@Injectable()
export class TweetHttpService extends Stream<Timeline> {

  constructor(
    private http: HttpClient,
    private timelinesHttp: TimelinesHttpService,
  ) {
    super();
  }

  _fetchStream(): Observable<Timeline> {
    return this.http.get<Timeline>('/api/tweets');
  }

  createTweet(tweetCreate: TweetCreate): Promise<void> {
    return this.http
      .post('/api/tweet', tweetCreate, { responseType: 'text' })
      .do(() => this.timelinesHttp.refetch())
      .mapTo(undefined)
      .toPromise();
  }
}
