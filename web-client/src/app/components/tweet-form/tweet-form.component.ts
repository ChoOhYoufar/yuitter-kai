import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { TweetFormComponentService } from './tweet-form-component.service';
import { Account } from '../../models/account/account';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';

export interface TweetFormData {
  accounts: (Account & {
    checked: boolean
  })[];
  tweetText: string;
}

@Component({
  selector: 'ytr-tweet-form',
  templateUrl: './tweet-form.component.html',
  styleUrls: ['./tweet-form.component.scss'],
  providers: [TweetFormComponentService]
})
export class TweetFormComponent implements OnInit, OnDestroy {
  @Input() tweetFormContainer;

  form: FormGroup;
  accountsSubscription: Subscription;
  formLoaded = false;

  constructor(
    private service: TweetFormComponentService,
    private fb: FormBuilder,
  ) { }

  ngOnInit() {
    this.accountsSubscription = this.service.fetchMyAccounts().subscribe(myAccounts => {
      this.form = this.createForm(myAccounts);
      this.formLoaded = true;
    });
  }

  ngOnDestroy() {
    if (this.accountsSubscription) {
      this.accountsSubscription.unsubscribe();
    }
  }

  async submit() {
    // NOTE accountが選択されていなければ、エラー返したい。
    // NOTE validationの方法
    if (this.form.value.accounts.filter(a => a.checked).length < 1 || this.form.valid) {
      return;
    }

    await this.service.createTweet(this.form.value);
    this.form.reset();
    this.tweetFormContainer.close();
  }

  private createForm(myAccounts: Account[]): FormGroup {
    return this.fb.group({
      accounts: this.fb.array(myAccounts.map(account => {
        return this.fb.group(
          {
            ...account,
            checked: { value: false, disabled: false }
          }
        );
      })),
      tweetText: ['', [Validators.maxLength(140), Validators.required]]
    })
  }
}
