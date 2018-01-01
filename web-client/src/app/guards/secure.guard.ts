import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserHttpService } from '../repositories/http/user/user-http.service';
import { MatDialog } from '@angular/material';
import { SignInFormComponent } from '../components/sign-in-form/sign-in-form.component';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';

@Injectable()
export class SecureGuard implements CanActivate {
  constructor(
    private userHttp: UserHttpService,
    private dialog: MatDialog
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.userHttp.getLoginStatus().do(auth => {
      if (!auth) {
        this.openSignInForm();
      }
    });
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
