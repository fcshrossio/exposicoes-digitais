import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditsFormComponent } from './credits-form.component';

describe('CreditsFormComponent', () => {
  let component: CreditsFormComponent;
  let fixture: ComponentFixture<CreditsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditsFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
