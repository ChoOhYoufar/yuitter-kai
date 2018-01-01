import { Injectable } from '@angular/core';
import { MyAccountsHttpService } from '../../repositories/http/account/my-accounts-http.service';
import { Observable } from 'rxjs/Observable';
import { Account } from '../../models/account/account';

@Injectable()
export class BoardComponentService {

  constructor(private myAccountsHttp: MyAccountsHttpService) {
  }

  fetchMyAccounts(): Observable<Account[]> {
    return this.myAccountsHttp.getStream();
  }
}
