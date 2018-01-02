import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { TimelinesHttpService } from '../../repositories/http/timeline/timelines-http.service';
import { Timeline } from '../../models/timeline/timeline';

@Injectable()
export class BoardComponentService {
  constructor(private timelinesHttp: TimelinesHttpService) {
  }

  fetchTimelines(): Observable<Timeline[]> {
    return this.timelinesHttp.getStream();
  }
}
