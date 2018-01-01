import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Stream } from '../../stream/stream';
import { Account } from '../../../models/account/account';

@Injectable()
export class MyAccountsHttpService extends Stream<Account[]> {

  constructor(private http: HttpClient) {
    super();
  }

  _fetchStream(): Observable<Account[]> {
    return this.http.get<Account[]>('/api/accounts/me');
  }
}
