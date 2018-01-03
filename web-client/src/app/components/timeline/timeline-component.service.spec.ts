import { TestBed, inject } from '@angular/core/testing';

import { TimelineComponentService } from './timeline-component.service';

describe('TimelineComponentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TimelineComponentService]
    });
  });

  it('should be created', inject([TimelineComponentService], (service: TimelineComponentService) => {
    expect(service).toBeTruthy();
  }));
});
