import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AccountFormComponentService } from './account-form-component.service';
import { Account } from '../../models/account/account';

@Component({
  selector: 'ytr-account-form',
  templateUrl: './account-form.component.html',
  styleUrls: ['./account-form.component.scss'],
  providers: [AccountFormComponentService]
})
export class AccountFormComponent implements OnInit {
  form: FormGroup;

  @Input() accountFormContainer;

  constructor(
    private fb: FormBuilder,
    private service: AccountFormComponentService,
  ) { }

  ngOnInit() {
    this.form = this.createForm();
  }

  createForm(): FormGroup {
    return this.fb.group({
      accountName: '',
      avatar: ''
    })
  }

  async submit(): Promise<void> {
    const account: Account = {
      ...this.form.value,
      accountStatus: 'ENA'
    };

    await this.service.createAccount(account);
    this.accountFormContainer.close();
  }
}
