import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceInfoSupplierComponent } from './service-info-supplier.component';

describe('ServiceInfoSupplierComponent', () => {
  let component: ServiceInfoSupplierComponent;
  let fixture: ComponentFixture<ServiceInfoSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ServiceInfoSupplierComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceInfoSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
