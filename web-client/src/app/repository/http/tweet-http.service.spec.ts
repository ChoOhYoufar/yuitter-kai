import { TestBed, inject } from '@angular/core/testing';

import { TweetHttpService } from './tweet-http.service';

describe('TweetHttpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TweetHttpService]
    });
  });

  it('should be created', inject([TweetHttpService], (service: TweetHttpService) => {
    expect(service).toBeTruthy();
  }));
});
