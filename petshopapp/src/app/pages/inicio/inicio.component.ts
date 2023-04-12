import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { Petshop, Cliente } from 'src/app/petshop';
import { PetshopService } from 'src/app/petshop.service';




@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent {
  public pet : Petshop [] = [];
  public pet2: Petshop[] = [];
  public cliente: Cliente[] = [];
  public lucro: number  ;

  constructor ( private petshopService: PetshopService){}
 
  ngOnInit(): void {

    this.getRecentes();
    this.getClientesRecentes();
    this.getServicos();
    this.getlucro();
    
    
  }

  public getServicos(): any{
    this.petshopService.getPet().subscribe(
      (response: Petshop[])=>{
        this.pet2 = response;
        console.log(response.length)
      }
    )
  }

  public getlucro(): any{
    this.petshopService.getLucro().subscribe(
      (response : number)=>{
        this.lucro = response;
        console.log(response)
      },(error: HttpErrorResponse) => {alert(error.message)}

    )
  }

  public getRecentes():void{
    this.petshopService.getRecentes().subscribe(
      (response: Petshop[])=>{
        this.pet = response;
        console.log(response.length)
      }, (error: HttpErrorResponse) => {alert(error.message)}
    
      )
     }

     public getClientesRecentes():void{
      this.petshopService.getClientesRecentes().subscribe(
        (response: Cliente[])=>{
          this.cliente = response;
          console.log(response.length)
        }, (error: HttpErrorResponse) => {alert(error.message)}
      
        )
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
    
    getResultado(valor: number) : number{
      return valor;
    }

}
