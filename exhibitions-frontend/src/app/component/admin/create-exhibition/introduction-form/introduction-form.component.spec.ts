import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntroductionFormComponent } from './introduction-form.component';

describe('IntroductionFormComponent', () => {
  let component: IntroductionFormComponent;
  let fixture: ComponentFixture<IntroductionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IntroductionFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IntroductionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
