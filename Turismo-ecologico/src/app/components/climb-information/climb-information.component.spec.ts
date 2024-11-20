import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClimbInformationComponent } from './climb-information.component';

describe('ClimbInformationComponent', () => {
  let component: ClimbInformationComponent;
  let fixture: ComponentFixture<ClimbInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClimbInformationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClimbInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
