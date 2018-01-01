import { Injectable } from '@angular/core';
import { MyAccountsHttpService } from '../../repositories/http/account/my-accounts-http.service';
import { Observable } from 'rxjs/Observable';
import { Account } from '../../models/account/account';
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
