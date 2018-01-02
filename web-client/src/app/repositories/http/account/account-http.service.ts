import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Account } from '../../../models/account/account'
import 'rxjs/add/operator/mapTo';
import 'rxjs/add/operator/do';
import { TimelinesHttpService } from '../timeline/timelines-http.service';
import { MyAccountsHttpService } from './my-accounts-http.service';

@Injectable()
export class AccountHttpService {

  constructor(
    private http: HttpClient,
    private timelinesHttp: TimelinesHttpService,
    private myAccountsHttp: MyAccountsHttpService,
  ) {}

  createAccount(account: Account): Promise<void> {
    return this.http.post('/api/account', account, { responseType: 'text' })
      .mapTo(undefined)
      .do(() => {
        this.timelinesHttp.refetch();
        this.myAccountsHttp.refetch();
      })
      .toPromise();
  }
}
