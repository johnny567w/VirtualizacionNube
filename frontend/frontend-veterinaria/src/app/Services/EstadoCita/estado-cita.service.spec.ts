import { TestBed } from '@angular/core/testing';

import { EstadoCitaService } from './estado-cita.service';

describe('EstadoCitaService', () => {
  let service: EstadoCitaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EstadoCitaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
