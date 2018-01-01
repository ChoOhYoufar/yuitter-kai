import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Timeline } from '../../models/timeline/timeline';
import { TimelineComponentService } from './timeline-component.service';

@Component({
  selector: 'ytr-timeline-container',
  templateUrl: './timeline-container.component.html',
  styleUrls: ['./timeline-container.component.scss'],
  providers: [TimelineComponentService]
})
export class TimelineContainerComponent implements OnInit {
  @Input() accountId: number;
  timeline$: Observable<Timeline>;

  constructor(private service: TimelineComponentService) { }

  ngOnInit() {
    this.timeline$ = this.service.fetchTimeline(this.accountId)
  }
}
