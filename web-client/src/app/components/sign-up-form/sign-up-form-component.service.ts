import { Injectable } from '@angular/core';
import { AuthInfo } from '../../models/user/auth-info';
import { UserHttpService } from '../../repositories/http/user/user-http.service';

@Injectable()
export class SignUpFormComponentService {

  constructor(private userHttp: UserHttpService) {}

  signUp(authInfo: AuthInfo): Promise<void> {
    return this.userHttp.signUp(authInfo);
  }
}
