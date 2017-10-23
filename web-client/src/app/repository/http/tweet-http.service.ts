import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Stream } from '../stream/stream';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Timeline } from '../../models/timeline/timeline';


@Injectable()
export class TweetHttpService extends Stream<Timeline>{

  constructor(
    private http: Http
  ) {
    super();
  }

  _fetchStream(): Observable<Timeline> {
    return this.http.get('api/tweets').map(res => {
      var timeline = res.json() as Timeline;
      console.log(res.json() as Timeline);
      return res.json() as Timeline;
    });
  }

}
