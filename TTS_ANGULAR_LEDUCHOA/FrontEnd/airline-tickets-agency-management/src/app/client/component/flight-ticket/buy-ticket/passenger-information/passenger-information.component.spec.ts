import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassengerInformationComponent } from './passenger-information.component';

describe('PassengerInformationComponent', () => {
  let component: PassengerInformationComponent;
  let fixture: ComponentFixture<PassengerInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassengerInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassengerInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
