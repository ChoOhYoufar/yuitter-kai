import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stream } from '../../stream/stream';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Timeline } from '../../../models/timeline/timeline';

@Injectable()
export class TweetHttpService extends Stream<Timeline>{

  constructor(
    private http: HttpClient
  ) {
    super();
  }

  _fetchStream(): Observable<Timeline> {
    return this.http.get<Timeline>('api/tweets');
  }
}
