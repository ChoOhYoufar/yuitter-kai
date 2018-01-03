import { Component, Input, OnInit } from '@angular/core';
import { Timeline } from '../../models/timeline/timeline';
import { TimelineComponentService } from './timeline-component.service';

@Component({
  selector: 'ytr-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss'],
  providers: [TimelineComponentService]
})
export class TimelineComponent implements OnInit {
  @Input() timeline: Timeline;

  constructor() {
  }

  ngOnInit() {
  }
}
