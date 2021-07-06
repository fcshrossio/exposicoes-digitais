import { TestBed } from '@angular/core/testing';

import { ExhibitionItemService } from './exhibition-item.service';

describe('ExhibitionItemService', () => {
  let service: ExhibitionItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExhibitionItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
