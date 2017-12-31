import { Injectable } from '@angular/core';
import { UserHttpService } from '../../repositories/http/user/user-http.service';

@Injectable()
export class SessionService {

  constructor(
     userHttp: UserHttpService
  ) { }

}
