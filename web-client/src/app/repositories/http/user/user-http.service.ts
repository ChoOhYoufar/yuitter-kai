import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../../models/user/user';
import { Stream } from '../../stream/stream';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserHttpService extends Stream<User> {

  constructor(
    private http: HttpClient
  ) {
    super();
  }

  _fetchStream(): Observable<User> {
    return this.http.get<User>('api/user');
  }

  getLoginStatus(): Observable<boolean> {
    return this.getStream().map(this.judgeLogin)
  }

  private judgeLogin(user: any): boolean {
    return !!user;
  }
}
