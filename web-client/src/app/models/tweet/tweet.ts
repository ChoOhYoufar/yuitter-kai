import { TweetInfo } from './tweet-info';
import { AccountInfo } from '../account/account-info';

// ツイート本来のデータと紐づくアカウントデータを保持
export class Tweet {

  info: TweetInfo;
  account: AccountInfo;
}
