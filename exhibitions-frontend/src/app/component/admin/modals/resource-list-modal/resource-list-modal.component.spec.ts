import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceListModalComponent } from './resource-list-modal.component';

describe('ResourceListModalComponent', () => {
  let component: ResourceListModalComponent;
  let fixture: ComponentFixture<ResourceListModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResourceListModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourceListModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
