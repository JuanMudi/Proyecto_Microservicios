import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GaleryClientComponent } from './galery-client.component';

describe('GaleryClientComponent', () => {
  let component: GaleryClientComponent;
  let fixture: ComponentFixture<GaleryClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GaleryClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GaleryClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
