import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { TimelineHttpService } from '../../repositories/http/timeline/timeline-http.service';
import { Timeline } from '../../models/timeline/timeline';

@Injectable()
export class TimelineComponentService {

  constructor(private timelineHttp: TimelineHttpService) { }

  fetchTimeline(accountId: number): Observable<Timeline> {
    return this.timelineHttp.getStream(accountId);
  }
}
