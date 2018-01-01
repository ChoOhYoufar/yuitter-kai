import { Injectable } from '@angular/core';
import { UserHttpService } from '../../repositories/http/user/user-http.service';
import { AuthInfo } from '../../models/user/auth-info';

@Injectable()
export class SignInFormComponentService {

  constructor(private userHttp: UserHttpService) {}

  signIn(authInfo: AuthInfo): Promise<void> {
    return this.userHttp.signIn(authInfo);
  }
}
