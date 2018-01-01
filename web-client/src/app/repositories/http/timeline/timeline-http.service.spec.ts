import { TestBed, inject } from '@angular/core/testing';

import { TimelineHttpService } from './timeline-http.service';

describe('TimelineHttpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TimelineHttpService]
    });
  });

  it('should be created', inject([TimelineHttpService], (service: TimelineHttpService) => {
    expect(service).toBeTruthy();
  }));
});
