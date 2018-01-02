import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Account } from '../../models/account/account';
import { MyAccountsHttpService } from '../../repositories/http/account/my-accounts-http.service';
import { TweetFormData } from './tweet-form.component';
import { TweetHttpService } from '../../repositories/http/tweet/tweet-http.service';

@Injectable()
export class TweetFormComponentService {

  constructor(
    private myAccountsHttp: MyAccountsHttpService,
    private tweetHttp: TweetHttpService
  ) { }

  fetchMyAccounts(): Observable<Account[]> {
    return this.myAccountsHttp.getStream();
  }

  createTweet(tweetFormData: TweetFormData): Promise<void> {
    const accountIds = tweetFormData.accounts
      .filter(a => a.checked)
      .map(a => a.accountId);
    const tweetCreate = {
      accountIds,
      tweetText: tweetFormData.tweetText
    };
    return this.tweetHttp.createTweet(tweetCreate);
  }
}
