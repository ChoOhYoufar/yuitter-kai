import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stream } from '../../stream/stream';
import { Timeline } from '../../../models/timeline/timeline';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TimelineHttpService extends Stream<Timeline> {

  constructor(private http: HttpClient) {
    super();
  }

  _fetchStream(accountId: number): Observable<Timeline> {
    return this.http.get<Timeline>(`/api/timeline/${accountId}`);
  }
}
