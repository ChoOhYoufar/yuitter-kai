import { Injectable } from '@angular/core';
import { Stream } from '../../stream/stream';
import { Account } from '../../../models/account/account';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AccountsHttpService extends Stream<Account[]> {

  constructor(private http: HttpClient) {
    super();
  }

  _fetchStream(): Observable<Account[]> {
    return this.http.get<Account[]>('/api/accounts');
  }
}
