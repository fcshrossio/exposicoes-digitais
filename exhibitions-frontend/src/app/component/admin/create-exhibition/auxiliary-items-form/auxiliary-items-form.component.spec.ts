import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuxiliaryItemsFormComponent } from './auxiliary-items-form.component';

describe('AuxiliaryItemsFormComponent', () => {
  let component: AuxiliaryItemsFormComponent;
  let fixture: ComponentFixture<AuxiliaryItemsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuxiliaryItemsFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuxiliaryItemsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
