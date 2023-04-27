import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaragendamentoComponent } from './listaragendamento.component';

describe('ListaragendamentoComponent', () => {
  let component: ListaragendamentoComponent;
  let fixture: ComponentFixture<ListaragendamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaragendamentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaragendamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
