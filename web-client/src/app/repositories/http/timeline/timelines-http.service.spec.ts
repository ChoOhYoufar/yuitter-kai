import { TestBed, inject } from '@angular/core/testing';

import { TimelinesHttpService } from './timelines-http.service';

describe('TimelinesHttpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TimelinesHttpService]
    });
  });

  it('should be created', inject([TimelinesHttpService], (service: TimelinesHttpService) => {
    expect(service).toBeTruthy();
  }));
});
