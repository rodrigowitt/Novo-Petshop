import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Agendamento, Cliente, Petshop } from './petshop';

@Injectable({
  providedIn: 'root'
})
export class PetshopService {
  private apiServerUrl = environment.apiBaseUrl;
  private apiServerClientUrl = environment.apiClienteUrl;


  constructor(private http: HttpClient){}

  public getPet(): Observable<Petshop[]>{
    return this.http.get<Petshop[]>(`${this.apiServerUrl}petshop`)
}
public getLucro(): Observable<number>{
  return this.http.get<number>(`${this.apiServerUrl}petshop/lucro`)
}
public getTotalPet(): Observable<Petshop[]>{
  return this.http.get<Petshop[]>(`${this.apiServerUrl}petshop`)
}
public getRel(dias: string | undefined, status : string | undefined): Observable<Petshop[]>{
  return this.http.get<Petshop[]>(`${this.apiServerUrl}petshop/relatorios/${dias}/${status}`)
}
public getRecentes(): Observable<Petshop[]>{
  return this.http.get<Petshop[]>(`${this.apiServerUrl}petshop/recentes`)
}
public getClientesRecentes(): Observable<Cliente[]>{
  return this.http.get<Cliente[]>(`${this.apiServerUrl}clientes/recentes`)
}
public getClient(): Observable<Cliente[]>{
  return this.http.get<Cliente[]>(`${this.apiServerClientUrl}clientes`)
}
public getAgendamento(): Observable<Agendamento[]>{
  return this.http.get<Agendamento[]>(`${this.apiServerUrl}agendamento`)
}
public getAgendamentoRecentes(): Observable<Agendamento[]>{
  return this.http.get<Agendamento[]>(`${this.apiServerUrl}agendamento/recentes`)
}
public addPet(petshop: Petshop): Observable<Petshop>{
  return this.http.post<Petshop>(`${this.apiServerUrl}petshop`, petshop)
}
public addAgendamento(agendamento: Agendamento): Observable<Agendamento>{
  return this.http.post<Agendamento>(`${this.apiServerUrl}agendamento`, agendamento)
}

public addClient(cliente: Cliente): Observable<Cliente>{
  return this.http.post<Cliente>(`${this.apiServerUrl}clientes`, cliente)
}

public updatePet(petId: string | undefined, petshop: Petshop): Observable<Petshop>{
  return this.http.put<Petshop>(`${this.apiServerUrl}petshop/${petId}`, petshop)
}
public updateCliente(clienteId: string | undefined, cliente: Cliente): Observable<Cliente>{
  return this.http.put<Cliente>(`${this.apiServerUrl}clientes/${clienteId}`, cliente)
}
public updateAgendamento(agendamentoId: string | undefined, agendamento: Agendamento): Observable<Agendamento>{
  return this.http.put<Agendamento>(`${this.apiServerUrl}agendamento/${agendamentoId}`, agendamento)
}
public deletePet(petId: string | undefined): Observable<void>{
  return this.http.delete<void>(`${this.apiServerUrl}petshop/${petId}`)
}
public deleteCliente(clienteId: string | undefined): Observable<void>{
  return this.http.delete<void>(`${this.apiServerUrl}clientes/${clienteId}`)
}
public deleteAgendamento(agendamentoId: string | undefined): Observable<void>{
  return this.http.delete<void>(`${this.apiServerUrl}agendamento/${agendamentoId}`)
}
public documentPet(petId: string | undefined): Observable<void>{
   return this.http.get<void>(`${this.apiServerUrl}petshop/download/${petId}`)
}
}
