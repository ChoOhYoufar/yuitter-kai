import { TestBed, inject } from '@angular/core/testing';

import { BoardComponentService } from './board-component.service';

describe('BoardComponentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BoardComponentService]
    });
  });

  it('should be created', inject([BoardComponentService], (service: BoardComponentService) => {
    expect(service).toBeTruthy();
  }));
});
