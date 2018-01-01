import { Component, OnInit } from '@angular/core';
import { BoardComponentService } from './board-component.service';
import { Observable } from 'rxjs/Observable';
import { Timeline } from '../../models/timeline/timeline';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
  providers: [BoardComponentService]
})
export class BoardComponent implements OnInit {
  timelines$: Observable<Timeline[]>;

  constructor(private service: BoardComponentService) {
    this.timelines$ = this.service.fetchTimelines();
  }

  ngOnInit() {
  }
}
