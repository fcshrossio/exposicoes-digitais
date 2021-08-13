import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhibitionListAdminComponent } from './exhibition-list-admin.component';

describe('ExhibitionListAdminComponent', () => {
  let component: ExhibitionListAdminComponent;
  let fixture: ComponentFixture<ExhibitionListAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExhibitionListAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhibitionListAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
