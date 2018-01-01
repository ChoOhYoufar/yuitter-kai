import { TestBed, inject } from '@angular/core/testing';

import { AccountsHttpService } from './accounts-http.service';

describe('AccountsHttpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AccountsHttpService]
    });
  });

  it('should be created', inject([AccountsHttpService], (service: AccountsHttpService) => {
    expect(service).toBeTruthy();
  }));
});
