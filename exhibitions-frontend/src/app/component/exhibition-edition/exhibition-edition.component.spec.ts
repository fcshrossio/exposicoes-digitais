import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhibitionEditionComponent } from './exhibition-edition.component';

describe('ExhibitionEditionComponent', () => {
  let component: ExhibitionEditionComponent;
  let fixture: ComponentFixture<ExhibitionEditionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExhibitionEditionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhibitionEditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
