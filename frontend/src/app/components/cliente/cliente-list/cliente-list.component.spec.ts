import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TecnicoListComponent } from './cliente-list.component';

describe('TecnicoListComponent', () => {
  let component: TecnicoListComponent;
  let fixture: ComponentFixture<TecnicoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TecnicoListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TecnicoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
