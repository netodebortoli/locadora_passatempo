import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { diretorResolver } from './diretor.resolver';

describe('diretorResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => diretorResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
