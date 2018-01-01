import { TestBed, inject } from '@angular/core/testing';

import { MyAccountsHttpService } from './my-accounts-http.service';

describe('MyAccountsHttpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyAccountsHttpService]
    });
  });

  it('should be created', inject([MyAccountsHttpService], (service: MyAccountsHttpService) => {
    expect(service).toBeTruthy();
  }));
});
