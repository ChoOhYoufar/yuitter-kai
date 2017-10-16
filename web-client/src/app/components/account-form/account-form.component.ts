import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ytr-account-form',
  templateUrl: './account-form.component.html',
  styleUrls: ['./account-form.component.scss']
})
export class AccountFormComponent implements OnInit {

  @Input() accountFormContainer;

  constructor() { }

  ngOnInit() {
  }

}
