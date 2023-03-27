import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Cliente, Petshop } from './petshop';

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
public getClient(): Observable<Cliente[]>{
  return this.http.get<Cliente[]>(`${this.apiServerClientUrl}clientes`)
}

public addPet(petshop: Petshop): Observable<Petshop>{
  return this.http.post<Petshop>(`${this.apiServerUrl}petshop`, petshop)
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
public deletePet(petId: string | undefined): Observable<void>{
  return this.http.delete<void>(`${this.apiServerUrl}petshop/${petId}`)
}
public deleteCliente(clienteId: string | undefined): Observable<void>{
  return this.http.delete<void>(`${this.apiServerUrl}clientes/${clienteId}`)
}
}
