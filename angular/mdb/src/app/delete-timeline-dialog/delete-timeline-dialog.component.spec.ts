import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteTimelineDialogComponent } from './delete-timeline-dialog.component';

describe('DeleteTimelineDialogComponent', () => {
  let component: DeleteTimelineDialogComponent;
  let fixture: ComponentFixture<DeleteTimelineDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteTimelineDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteTimelineDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
