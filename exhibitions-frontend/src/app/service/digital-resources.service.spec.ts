import { TestBed } from '@angular/core/testing';

import { DigitalResourcesService } from './digital-resources.service';

describe('DigitalResourcesService', () => {
  let service: DigitalResourcesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DigitalResourcesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
