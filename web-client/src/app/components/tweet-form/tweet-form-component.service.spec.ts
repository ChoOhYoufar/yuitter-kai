import { TestBed, inject } from '@angular/core/testing';

import { TweetFormComponentService } from './tweet-form-component.service';

describe('TweetFormComponentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TweetFormComponentService]
    });
  });

  it('should be created', inject([TweetFormComponentService], (service: TweetFormComponentService) => {
    expect(service).toBeTruthy();
  }));
});
