import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { UserHttpService } from '../repositories/http/user/user-http.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';

@Injectable()
export class SecureGuard implements CanActivate {
  constructor(
    private userHttp: UserHttpService,
    private router: Router,
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.userHttp.getLoginStatus().do(auth => {
      if (!auth) {
        this.router.navigate(['./signin']);
      }
    });
  }
}
