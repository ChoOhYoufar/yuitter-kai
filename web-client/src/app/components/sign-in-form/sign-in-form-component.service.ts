import { Injectable } from '@angular/core';
import { UserHttpService } from '../../repositories/http/user/user-http.service';
import { AuthInfo } from '../../models/user/auth-info';
import { Router } from '@angular/router';
import 'rxjs/Subscription';

@Injectable()
export class SignInFormComponentService {

  constructor(
    private userHttp: UserHttpService,
    private router: Router,
  ) {}

  signIn(authInfo: AuthInfo): Promise<void> {
    return this.userHttp.signIn(authInfo);
  }

  canActivate(): void {
    this.userHttp.getLoginStatus().subscribe(auth => {
      if (auth) {
        this.router.navigate(['/home']);
      }
    });
  }
}
