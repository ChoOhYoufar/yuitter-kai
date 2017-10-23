import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Stream } from '../stream/stream';
import { Observable } from 'rxjs/Observable';
import { TweetList } from '../../models/tweet/tweet-list';
import 'rxjs/add/operator/map';


@Injectable()
export class TweetHttpService extends Stream<TweetList>{

  constructor(
    private http: Http
  ) {
    super();
  }

  _fetchStream(): Observable<any> {
    return this.http.get('api/tweets').map(res => {
      console.log(res.json() as TweetList);
      return res.json() as TweetList;
    })
  }
}
