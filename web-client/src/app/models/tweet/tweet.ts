import { TweetInfo } from './tweet-info';
import { Account } from '../account/account';

// ツイート本来のデータと紐づくアカウントデータを保持
export class Tweet {

  info: TweetInfo;
  account: Account
}
