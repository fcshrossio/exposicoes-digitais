import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhibitionListHomepageComponent } from './exhibition-list-homepage.component';

describe('ExhibitionListHomepageComponent', () => {
  let component: ExhibitionListHomepageComponent;
  let fixture: ComponentFixture<ExhibitionListHomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExhibitionListHomepageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhibitionListHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
