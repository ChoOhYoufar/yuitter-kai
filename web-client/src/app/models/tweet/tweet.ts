import { Account } from '../account/account';
import DateTimeFormat = Intl.DateTimeFormat;

// ツイート本来のデータと紐づくアカウントデータを保持
export class Tweet {
  tweetId: number;
  tweetText: string;
  registerDatetime: DateTimeFormat;
  account: Account;
}
