import { Injectable } from '@angular/core';
import { AccountHttpService } from '../../repositories/http/account/account-http.service';
import { Account } from '../../models/account/account'

@Injectable()
export class AccountFormComponentService {

  constructor(private accountHttp: AccountHttpService) {}

  createAccount(account: Account): Promise<void> {
    return this.accountHttp.createAccount(account);
  }
}
