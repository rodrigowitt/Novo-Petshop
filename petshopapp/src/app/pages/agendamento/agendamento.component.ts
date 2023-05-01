import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Agendamento, Petshop } from 'src/app/petshop';
import { PetshopService } from 'src/app/petshop.service';


@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent {
  data: string;
  datePipe: any;

  

  constructor ( private petshopService: PetshopService){
    this.data = new Date().toLocaleDateString('pt-BR');

  };

  
   

  public onAddPet(addForm: NgForm) : void{
    document.getElementById('add-agendamento-form')?.click();
      this.petshopService.addAgendamento(addForm.value).subscribe(
        (response: Agendamento) => {
          console.log(response)
          addForm.reset();
          location.reload();
  
        },
        (error: HttpErrorResponse)=>{
          alert(error.message)
        }
        )
      }

    

}
