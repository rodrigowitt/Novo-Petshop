import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PetshopService } from './petshop.service';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { RouterModule, Routes } from '@angular/router';
import { PetsComponent } from './pages/pets/pets.component';


const routes: Routes = [
  { path: 'clientes', component: ClientesComponent },
  { path: 'inicio', component: PetsComponent },
 
];



@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    PetsComponent,
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule, 
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [PetshopService],
  bootstrap: [AppComponent]
})
export class AppModule { }


