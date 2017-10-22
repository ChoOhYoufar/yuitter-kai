import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Stream } from '../stream/stream';
import { Tweet } from '../../models/tweet/tweet';
import { Observable } from 'rxjs/Observable';
import { TweetList } from '../../models/tweet/tweet-list';

@Injectable()
export class TweetHttpService extends Stream<TweetList>{

  constructor(
    private http: Http
  ) {
    super();
  }

  _fetchStream(): Observable<TweetList> {
    // return this.http.get('api/tweets').subscribe(
    //   response => response.json() as Tweet[]
    // );
    //
    // return this.http.get<{ users: CorporateUser[] }>(`/api/corporate/users?${toQueryString(query)}`)
    //   .map(res => res.users.map(user => ({ ...user, loginAt: user.loginAt ? moment.utc(user.loginAt) : undefined })));
  }


}
