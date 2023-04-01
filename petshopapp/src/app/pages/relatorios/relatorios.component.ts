import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Petshop } from 'src/app/petshop';
import { PetshopService } from 'src/app/petshop.service';

@Component({
  selector: 'app-relatorios',
  templateUrl: './relatorios.component.html',
  styleUrls: ['./relatorios.component.css']
})
export class RelatoriosComponent {
  public pet : Petshop [] = [];
  valorSelecionado: string;
  statusSelecionado: string;
  public editPet : Petshop | null;
  public deletePet : Petshop | null;
  public docPet: Petshop | null;
  

  constructor ( private petshopService: PetshopService){}

  
 public getRelatorios(valorSelecionado : string, statusSelecionado : string):void{
    this.petshopService.getRel(valorSelecionado,statusSelecionado).subscribe(
      (response: Petshop[])=>{
        this.pet = response;
        console.log(response)
      }, (error: HttpErrorResponse) => {alert(error.message)}
    
      )
     }


     public onUpdatePet(petshop: Petshop, petId : string | undefined) :void{
        
      this.petshopService.updatePet(petId, petshop).subscribe(
        (response: Petshop)=>{
          console.log(response);
          this.getRelatorios(this.valorSelecionado, this.statusSelecionado);
        },
        (error: HttpErrorResponse)=>{
          alert(error.message)
        }
      )
      }

      public onDeletePet(petId: string | undefined) :void{
            
        this.petshopService.deletePet(petId).subscribe(
          (response : void) =>{
            this.getRelatorios(this.valorSelecionado, this.statusSelecionado);
            },
            (error: HttpErrorResponse)=>{
              alert(error.message)
            }
        )
      }

      public onDocumentPet(petId: string | undefined) :void{
            
        this.petshopService.documentPet(petId).subscribe(
          (response : void) =>{
            this.getRelatorios(this.valorSelecionado, this.statusSelecionado);
            window.open("../assets/Cadastro.pdf")
            },
            (error: HttpErrorResponse)=>{
              alert(error.message)
            }
        )
      }

      public onOpenModal(petshop: Petshop | null, mode: String): void{
        const container = document.getElementById('main-container')
        const button = document.createElement('button');
        button.type = 'button';
        button.style.display = 'none';
        button.setAttribute('data-toggle', 'modal')
        if(mode === 'add'){
          button.setAttribute('data-target', '#addPetModal')
        }
        if(mode === 'addClient'){
          button.setAttribute('data-target', '#addClienteModal')
        }
        if(mode === 'update'){
          this.editPet = petshop;
          button.setAttribute('data-target', '#updatePetModal3')
        }
        if(mode === 'delete'){
          this.deletePet = petshop;
          button.setAttribute('data-target', '#deletePetModal2')
        }
        if(mode === 'docPet'){
          this.docPet = petshop;
          button.setAttribute('data-target', '#documentPetModal')
        }
        container?.appendChild(button);
        button.click();
      }

      public searchTratamentos(key: string) : void{
        const results: Petshop[] = [];
        for(const tratamento of this.pet){
          if(tratamento.nome?.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
          tratamento.raca?.toLowerCase().indexOf(key.toLowerCase()) !== -1
          
           ){
            
            results.push(tratamento)
          }
        }
        this.pet = results;
        if(results.length === 0 || !key){
          this.getRelatorios(this.valorSelecionado, this.statusSelecionado);
        }
      }
      


}
