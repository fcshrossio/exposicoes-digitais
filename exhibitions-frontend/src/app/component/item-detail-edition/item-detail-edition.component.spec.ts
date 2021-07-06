import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemDetailEditionComponent } from './item-detail-edition.component';

describe('ItemDetailEditionComponent', () => {
  let component: ItemDetailEditionComponent;
  let fixture: ComponentFixture<ItemDetailEditionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemDetailEditionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemDetailEditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
