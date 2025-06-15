import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MomentoFormComponentComponent } from './momento-form-component.component';

describe('MomentoFormComponentComponent', () => {
  let component: MomentoFormComponentComponent;
  let fixture: ComponentFixture<MomentoFormComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MomentoFormComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MomentoFormComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
