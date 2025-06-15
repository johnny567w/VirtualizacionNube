import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MomentoComponentComponent } from './momento-component.component';

describe('MomentoComponentComponent', () => {
  let component: MomentoComponentComponent;
  let fixture: ComponentFixture<MomentoComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MomentoComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MomentoComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
