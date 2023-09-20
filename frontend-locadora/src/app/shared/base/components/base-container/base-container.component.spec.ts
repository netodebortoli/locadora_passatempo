import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseContainerComponent } from './base-container.component';

describe('BaseContainerComponent', () => {
  let component: BaseContainerComponent;
  let fixture: ComponentFixture<BaseContainerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BaseContainerComponent]
    });
    fixture = TestBed.createComponent(BaseContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
