import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhibitionShowcaseComponent } from './exhibition-showcase.component';

describe('ExhibitionShowcaseComponent', () => {
  let component: ExhibitionShowcaseComponent;
  let fixture: ComponentFixture<ExhibitionShowcaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExhibitionShowcaseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhibitionShowcaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
