
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Cliente, Petshop } from 'src/app/petshop';
import { PetshopService } from 'src/app/petshop.service';





@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class PetsComponent {
  public selectedValue: string ;
  public pet : Petshop [] = [];
  public cliente: Cliente[] = [];
  public editPet ?: Petshop | null;
  public deletePet : Petshop | null;
  public docPet: Petshop | null;
  public nrPreparando: any;
  public telefone: any;
  public total: any ;
  itemsPerPage: number = 10;
  currentPage: number = 1;
  totalPages: any = 1;
  pages: number[];
  response: any;


 

  

  

  
  
  ngOnInit(): void {
    this.getTratamentos();
    
    
    
   
  }
  constructor ( private petshopService: PetshopService){
    
            
  }

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
 
  public getTratamentos():any{
    this.petshopService.getPet().subscribe(
      (response: Petshop[])=>{
        this.pet = response;
        this.total = response;
        this.totalPages = Math.ceil(this.pet.length / this.itemsPerPage);
    this.pages = Array.from({length: this.totalPages}, (v, k) => k + 1);
    if (this.currentPage > this.totalPages) {
      this.currentPage = this.totalPages;
    }
      }, (error: HttpErrorResponse) => {alert(error.message)}
    
      )
     }



        public onUpdatePet(petshop: Petshop, petId : string | undefined) :void{
        
          this.petshopService.updatePet(petId, petshop).subscribe(
            (response: Petshop)=>{
              console.log(response);
              this.getTratamentos();
              
            },
            (error: HttpErrorResponse)=>{
              alert(error.message)
            }
          )
          }

          public onDeletePet(petId: string | undefined) :void{
            
            this.petshopService.deletePet(petId).subscribe(
              (response : void) =>{
                this.getTratamentos();
                },
                (error: HttpErrorResponse)=>{
                  alert(error.message)
                }
            )
          }

          public onDocumentPet(petId: string | undefined) :void{
            
            this.petshopService.documentPet(petId).subscribe(
              (response : void) =>{
                this.getTratamentos();
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
              this.nrPreparando = petshop?.statusTratamento;
              button.setAttribute('data-target', '#updatePetModal2')
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
              this.getTratamentos();
            }
          }

          getClasseCor(valor: String): string {
            if (valor == "PREPARANDO") {
              return 'preparando';
            } else if (valor == "FINALIZADO") {
              return 'finalizado';
            } else if (valor == "CANCELADO") {
              return 'cancelado';
            } else {
              return '';
            }
          }


         
        }










    


