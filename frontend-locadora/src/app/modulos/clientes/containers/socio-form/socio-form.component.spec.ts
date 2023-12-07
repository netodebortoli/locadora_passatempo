import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocioFormComponent } from './socio-form.component';

describe('ClienteFormComponent', () => {
  let component: SocioFormComponent;
  let fixture: ComponentFixture<SocioFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SocioFormComponent]
    });
    fixture = TestBed.createComponent(SocioFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
