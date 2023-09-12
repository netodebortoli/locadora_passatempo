import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { atorResolver } from './ator.resolver';

describe('atorResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => atorResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
