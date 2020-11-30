import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbenComponent } from './alben.component';

describe('AlbenComponent', () => {
  let component: AlbenComponent;
  let fixture: ComponentFixture<AlbenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlbenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlbenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
