import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Account } from '../../../models/account/account'
import 'rxjs/add/operator/mapTo';

@Injectable()
export class AccountHttpService {

  constructor(private http: HttpClient) {}

  createAccount(account: Account): Promise<void> {
    return this.http.post('/api/account', account, { responseType: 'text' })
      .mapTo(undefined)
      .toPromise()
  }
}
