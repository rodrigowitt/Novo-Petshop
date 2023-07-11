import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Agendamento } from 'src/app/petshop';
import { PetshopService } from 'src/app/petshop.service';

@Component({
  selector: 'app-listaragendamento',
  templateUrl: './listaragendamento.component.html',
  styleUrls: ['./listaragendamento.component.css']
})
export class ListaragendamentoComponent {
  public agendar : Agendamento [] = [];
  public editAgenda ?: Agendamento | null;
  public deleteAgenda : Agendamento | null;
  itemsPerPage: number = 10;
  currentPage: number = 1;
  totalPages: any = 1;
  pages: number[];

  constructor ( private petshopService: PetshopService){}

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
    }
  }

  setPage(page: number) {
    this.currentPage = page;
  }
 

  ngOnInit(): void {
    this.getAgendamentos();
    
  }

  public getAgendamentos():void{
    this.petshopService.getAgendamento().subscribe(
      (response: Agendamento[])=>{
        this.agendar = response;
        this.totalPages = Math.ceil(this.agendar.length / this.itemsPerPage);
    this.pages = Array.from({length: this.totalPages}, (v, k) => k + 1);
    if (this.currentPage > this.totalPages) {
      this.currentPage = this.totalPages;
    }
        console.log("Agendamento = " + response)
      },(error: HttpErrorResponse) => {alert(error.message)}
    
      )
     }

     public onUpdateAgenda(agenda: Agendamento, agendaId : string | undefined) :void{
        
      this.petshopService.updateAgendamento(agendaId, agenda).subscribe(
        (response: Agendamento)=>{
          console.log(response);
          location.reload();
          this.getAgendamentos();
          
        },
        (error: HttpErrorResponse)=>{
          alert(error.message)
        }
      )
      }

      public onDeleteAgenda(agendaId: string | undefined) :void{
            
        this.petshopService.deleteAgendamento(agendaId).subscribe(
          (response : void) =>{
            this.getAgendamentos();
            },
            (error: HttpErrorResponse)=>{
              alert(error.message)
            }
        )
      }

      public onOpenModal(agenda: Agendamento | null, mode: String): void{
        const container = document.getElementById('main-container')
        const button = document.createElement('button');
        button.type = 'button';
        button.style.display = 'none';
        button.setAttribute('data-toggle', 'modal')
        if(mode === 'add'){
          button.setAttribute('data-target', '#addAgendaModal')
        }
        if(mode === 'update'){
          this.editAgenda = agenda;
          button.setAttribute('data-target', '#updateAgendaModal3')
        }
        if(mode === 'delete'){
          this.deleteAgenda = agenda;
          button.setAttribute('data-target', '#deleteAgendaModal3')
        }
        container?.appendChild(button);
        button.click();
      }

      public searchAgendamentos(key: string) : void{
        const results: Agendamento[] = [];
        for(const agenda of this.agendar){
          if(agenda.nome?.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
          agenda.responsavel?.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
          agenda.data?.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
          agenda.horario?.toLowerCase().indexOf(key.toLowerCase()) !== -1
           ){
            
            results.push(agenda)
          }
        }
        this.agendar = results;
        if(results.length === 0 || !key){
          this.getAgendamentos();
        }
      }

}
