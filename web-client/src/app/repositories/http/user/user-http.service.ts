import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from '../../../models/user/user';
import { Stream } from '../../stream/stream';
import { Observable } from 'rxjs/Observable';
import { AuthInfo } from '../../../models/user/auth-info';
import 'rxjs/add/operator/mapTo';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';

@Injectable()
export class UserHttpService extends Stream<User> {

  constructor(
    private http: HttpClient
  ) {
    super();
  }

  _fetchStream(): Observable<User> {
    return this.http.get<User>('api/user')
      .catch((err: HttpErrorResponse) => {
        if (
          err.status === 401 &&
          err.error.code === 'error.unauthorized'
        ) {
          return Observable.of(undefined);
        } else {
          return Observable.throw(err);
        }
      });
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
