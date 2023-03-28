import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';

import { AppComponent } from './app.component';
import { PetshopService } from './petshop.service';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { Router, RouterModule, Routes } from '@angular/router';
import { PetsComponent } from './pages/pets/pets.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { provideAnimations } from '@angular/platform-browser/animations';



const routes: Routes = [
  
  { path: 'clientes', component: ClientesComponent },
  { path: 'inicio', component: PetsComponent },
  { path: '', pathMatch:'full',redirectTo:'inicio'},
  
 
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
    MatDatepickerModule,
    MatButtonModule,
    MatFormFieldModule,
    MatNativeDateModule, 
    MatInputModule,
    RouterModule.forRoot(routes),
    
    
  ],
  providers: [PetshopService, provideAnimations()],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }




