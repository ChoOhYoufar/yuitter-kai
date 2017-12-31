import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserHttpService } from '../repositories/http/user/user-http.service';
import { MatDialog } from '@angular/material';
import { SignInFormComponent } from '../components/sign-in-form/sign-in-form.component';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SecureGuard implements CanActivate {
  constructor(
    private userHttp: UserHttpService,
    private dialog: MatDialog
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {
    let auth = false;
    const status$ = this.userHttp.getLoginStatus();
    status$.subscribe(a => auth = a);
    if (auth) {
      auth = true;
    } else {
      this.openSignInForm();
    }
    return auth;
  }

  private openSignInForm() {
    const dialog = this.dialog.open(SignInFormComponent, {
      disableClose: true,
      width: '500px',
      position: {
        top: '15%'
      }
    });
    dialog.afterClosed().subscribe(result => {
      if (result != null) {
      }
    });
  }
}
