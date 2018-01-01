import { Component, OnInit } from '@angular/core';
import { BoardComponentService } from './board-component.service';
import { Observable } from 'rxjs/Observable';
import { Account } from '../../models/account/account';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
  providers: [BoardComponentService]
})
export class BoardComponent implements OnInit {
  myAccounts$: Observable<Account[]>;

  constructor(private service: BoardComponentService) {
    this.myAccounts$ = this.service.fetchMyAccounts();
  }

  ngOnInit() {
  }
}
