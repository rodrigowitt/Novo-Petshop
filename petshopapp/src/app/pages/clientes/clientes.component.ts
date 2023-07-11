import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Cliente, Petshop } from 'src/app/petshop';
import { PetshopService } from 'src/app/petshop.service';


@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent {
  title = 'petshopapp';
  public pet : Petshop [] = [];
  public cliente: Cliente[] = [];
  public editPet : Petshop | null;
  public editCliente : Cliente | null;
  public deletePet : Petshop | null;
  public deleteCliente : Cliente | null;
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
    this.getClientes();
    
  }

  public getClientes():void{
    this.petshopService.getClient().subscribe(
      (response: Cliente[])=>{
        this.cliente = response;
        this.totalPages = Math.ceil(this.cliente.length / this.itemsPerPage);
    this.pages = Array.from({length: this.totalPages}, (v, k) => k + 1);
    if (this.currentPage > this.totalPages) {
      this.currentPage = this.totalPages;
    }
        console.log(response);
      }, (error: HttpErrorResponse) => {alert(error.message)}
    
      )
     }

     public onOpenModal(cliente: Cliente | null, mode: String): void{
      const container = document.getElementById('main-container')
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal')
      if(mode === 'addClient'){
        button.setAttribute('data-target', '#addClienteModal')
      }
      if(mode === 'update'){
        this.editCliente = cliente;
        button.setAttribute('data-target', '#updateClientModal')
      }
      if(mode === 'delete'){
        this.deleteCliente = cliente;
        button.setAttribute('data-target', '#deleteClientModal')
      }
      container?.appendChild(button);
      button.click();
    }

    public onUpdateCliente(cliente: Cliente, clienteId : string | undefined) :void{
        
      this.petshopService.updateCliente(clienteId, cliente).subscribe(
        (response: Cliente)=>{
          console.log(response);
          this.getClientes();
        },
        (error: HttpErrorResponse)=>{
          alert(error.message)
        }
      )
      }

      public onDeletePet(clienteId: string | undefined) :void{
            
        this.petshopService.deleteCliente(clienteId).subscribe(
          (response : void) =>{
            this.getClientes();
            },
            (error: HttpErrorResponse)=>{
              alert(error.message)
            }
        )
      }



     public searchClientes(key: string) : void{
      const results: Cliente[] = [];
      for(const cliente of this.cliente){
        if(cliente.nome?.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
        cliente.telefone?.toLowerCase().indexOf(key.toLowerCase()) !== -1
        
         ){
          
          results.push(cliente)
        }
      }
      this.cliente = results;
      if(results.length === 0 || !key){
        this.getClientes();
      }
    }

}


