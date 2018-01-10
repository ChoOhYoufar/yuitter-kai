import { Injectable } from '@angular/core';
import { AuthInfo } from '../../models/user/auth-info';
import { UserHttpService } from '../../repositories/http/user/user-http.service';
import { Router } from '@angular/router';

@Injectable()
export class SignUpFormComponentService {

  constructor(
    private userHttp: UserHttpService,
    private router: Router,
  ) {}

  signUp(authInfo: AuthInfo): Promise<void> {
    return this.userHttp.signUp(authInfo);
  }

  // NOTE yuito sign inと同じロジックだから抽象化したい
  canActivate(): void {
    this.userHttp.getLoginStatus().subscribe(auth => {
      if (auth) {
        this.router.navigate(['/home']);
      }
    });
  }
}
