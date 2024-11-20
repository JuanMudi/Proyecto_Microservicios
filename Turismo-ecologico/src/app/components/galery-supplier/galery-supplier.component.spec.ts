import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GalerySupplierComponent } from './galery-supplier.component';

describe('GalerySupplierComponent', () => {
  let component: GalerySupplierComponent;
  let fixture: ComponentFixture<GalerySupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GalerySupplierComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GalerySupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
