import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../../models/user/user';
import { Stream } from '../../stream/stream';
import { Observable } from 'rxjs/Observable';
import { AuthInfo } from '../../../models/user/auth-info';
import 'rxjs/add/operator/mapTo'

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

  signUp(authInfo: AuthInfo): Promise<void> {
    return this.http.post('api/auth/signup', authInfo, { responseType: 'text' })
      .mapTo(undefined)
      .toPromise()
  }

  signIn(authInfo: AuthInfo): Promise<void> {
    return this.http.post('api/auth/signin', authInfo, { responseType: 'text' })
      .mapTo(undefined)
      .toPromise()
  }

  private judgeLogin(user: any): boolean {
    return !!user;
  }
}
