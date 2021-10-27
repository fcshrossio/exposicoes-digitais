import { TestBed } from '@angular/core/testing';

import { SavedResourcesService } from './saved-resources.service';

describe('SavedResourcesService', () => {
  let service: SavedResourcesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SavedResourcesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
