import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MomentoListComponentComponent } from './momento-list-component.component';

describe('MomentoListComponentComponent', () => {
  let component: MomentoListComponentComponent;
  let fixture: ComponentFixture<MomentoListComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MomentoListComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MomentoListComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
