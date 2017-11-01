import { InMemoryDbService } from 'angular-in-memory-web-api';
import DateTimeFormat = Intl.DateTimeFormat;

export class MockDbData implements InMemoryDbService {

  createDb() {
    let tweets = {
      account: {
        accountName: 'テストアカ1',
        avatar: 'https://www.min-inuzukan.com/images/detailMain_chihuahua.png?20170926'
      },
      tweetList: {
        value: [
        {
          tweetText: 'おはよう',
          imageUrl: 'https://www.google.co.jp/search?q=%E3%82%AF%E3%82%B8%E3%83%A9&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjl_M6d_IPXAhVGW7wKHcjkD5QQ_AUICigB&biw=1440&bih=799#',
          registerDatetime: '2017-03-08 05:35:57',
          account: {
            accountName: 'テストアカ1',
            avatar: 'https://www.google.co.jp/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwi3_7vj_IPXAhVGQLwKHZkkDLwQjRwIBw&url=https%3A%2F%2Fmatome.naver.jp%2Fodai%2F2142396408839928901&psig=AOvVaw1PbuYjKlYDY4ReO48RPnIL&ust=1508752880091664'
          }
        },
        {
          tweetText: 'おはよう',
          imageUrl: 'https://www.google.co.jp/search?q=%E3%82%AF%E3%82%B8%E3%83%A9&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjl_M6d_IPXAhVGW7wKHcjkD5QQ_AUICigB&biw=1440&bih=799#',
          registerDatetime: '2017-03-08 05:35:57',
          account: {
            accountName: 'テストアカ1',
            avatar: 'https://www.google.co.jp/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwi3_7vj_IPXAhVGQLwKHZkkDLwQjRwIBw&url=https%3A%2F%2Fmatome.naver.jp%2Fodai%2F2142396408839928901&psig=AOvVaw1PbuYjKlYDY4ReO48RPnIL&ust=1508752880091664'
          }
        }
      ]}
    };

    let users = [
      {
        email: 'hogehoge@gmail.com'
      }
    ];
    return { tweets: tweets, users: users }
  }
}
