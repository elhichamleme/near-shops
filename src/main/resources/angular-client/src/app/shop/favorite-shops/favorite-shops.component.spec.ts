import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoriteShopsComponent } from './favorite-shops.component';

describe('FavoriteShopsComponent', () => {
  let component: FavoriteShopsComponent;
  let fixture: ComponentFixture<FavoriteShopsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FavoriteShopsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FavoriteShopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
