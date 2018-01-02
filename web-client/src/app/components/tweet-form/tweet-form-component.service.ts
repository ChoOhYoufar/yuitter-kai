import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Account } from '../../models/account/account';
import { MyAccountsHttpService } from '../../repositories/http/account/my-accounts-http.service';

@Injectable()
export class TweetFormComponentService {

  constructor(private myAccountsHttp: MyAccountsHttpService) { }

  fetchMyAccounts(): Observable<Account[]> {
    return this.myAccountsHttp.getStream();
  }
}
