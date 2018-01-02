import { Injectable } from '@angular/core';
import { Stream } from '../../stream/stream';
import { Timeline } from '../../../models/timeline/timeline';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TimelinesHttpService extends Stream<Timeline[]> {

  constructor(private http: HttpClient) {
    super();
  }

  _fetchStream(): Observable<Timeline[]> {
    return this.http.get<Timeline[]>('/api/timelines');
  }
}
