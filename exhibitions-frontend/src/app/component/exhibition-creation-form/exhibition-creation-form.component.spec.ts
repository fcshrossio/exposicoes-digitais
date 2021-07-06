import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhibitionCreationFormComponent } from './exhibition-creation-form.component';

describe('ExhibitionCreationFormComponent', () => {
  let component: ExhibitionCreationFormComponent;
  let fixture: ComponentFixture<ExhibitionCreationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExhibitionCreationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhibitionCreationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
