import { TestBed, inject } from '@angular/core/testing';

import { AccountFormComponentService } from './account-form-component.service';

describe('AccountFormComponentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AccountFormComponentService]
    });
  });

  it('should be created', inject([AccountFormComponentService], (service: AccountFormComponentService) => {
    expect(service).toBeTruthy();
  }));
});
