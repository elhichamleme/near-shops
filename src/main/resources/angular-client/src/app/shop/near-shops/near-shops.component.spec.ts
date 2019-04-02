import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NearShopsComponent } from './near-shops.component';

describe('NearShopsComponent', () => {
  let component: NearShopsComponent;
  let fixture: ComponentFixture<NearShopsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NearShopsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NearShopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
