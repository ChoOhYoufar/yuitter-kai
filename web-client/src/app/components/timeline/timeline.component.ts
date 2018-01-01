import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Timeline } from '../../models/timeline/timeline';
import { TimelineService } from '../../services/timeline/timeline.service';
import { TimelineComponentService } from '../timeline-container/timeline-component.service';

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
