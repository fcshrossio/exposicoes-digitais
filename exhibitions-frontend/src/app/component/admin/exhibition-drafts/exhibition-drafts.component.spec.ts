import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhibitionDraftsComponent } from './exhibition-drafts.component';

describe('ExhibitionDraftsComponent', () => {
  let component: ExhibitionDraftsComponent;
  let fixture: ComponentFixture<ExhibitionDraftsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExhibitionDraftsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhibitionDraftsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
