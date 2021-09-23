import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhibitionPageComponent } from './exhibition-page.component';

describe('ExhibitionPageComponent', () => {
  let component: ExhibitionPageComponent;
  let fixture: ComponentFixture<ExhibitionPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExhibitionPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhibitionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
