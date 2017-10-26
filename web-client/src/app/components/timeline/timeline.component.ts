import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Timeline } from '../../models/timeline/timeline';
import { TimelineService } from '../../feature/timeline/timeline.service';

@Component({
  selector: 'ytr-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {

  timeline: Observable<Timeline>;

  constructor(
   private timelineService: TimelineService
  ) {
    this.timeline = this.timelineService.fetchTimeline();
    console.log(this.timeline);
  }

  ngOnInit() {

  }

}
