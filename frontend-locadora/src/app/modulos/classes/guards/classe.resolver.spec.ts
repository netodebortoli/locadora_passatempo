import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { classeResolver } from './classe.resolver';

describe('classeResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => classeResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
