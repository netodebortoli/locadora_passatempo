import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocacoesListComponent } from './locacoes-list.component';

describe('LocacoesListComponent', () => {
  let component: LocacoesListComponent;
  let fixture: ComponentFixture<LocacoesListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocacoesListComponent]
    });
    fixture = TestBed.createComponent(LocacoesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
