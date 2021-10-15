import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceShowcaseModalComponent } from './resource-showcase-modal.component';

describe('ResourceShowcaseModalComponent', () => {
  let component: ResourceShowcaseModalComponent;
  let fixture: ComponentFixture<ResourceShowcaseModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResourceShowcaseModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourceShowcaseModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
