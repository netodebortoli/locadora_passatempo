import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SociosListComponent } from './socios-list.component';

describe('ClientesListComponent', () => {
  let component: SociosListComponent;
  let fixture: ComponentFixture<SociosListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SociosListComponent]
    });
    fixture = TestBed.createComponent(SociosListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
