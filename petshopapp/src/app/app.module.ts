import { LOCALE_ID, NgModule } from '@angular/core';
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
import { RelatoriosComponent } from './pages/relatorios/relatorios.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { CommonModule, registerLocaleData } from '@angular/common';
import { ReplacePipe } from './pages/inicio/replace.pipe';
import pt from '@angular/common/locales/pt';
import { AgendamentoComponent } from './pages/agendamento/agendamento.component';
import { ListaragendamentoComponent } from './pages/listaragendamento/listaragendamento.component';






const routes: Routes = [
  
  { path: 'clientes', component: ClientesComponent },
  { path: 'pets', component: PetsComponent },
  { path: 'relatorios', component: RelatoriosComponent},
  { path: 'inicio', component: InicioComponent},
  { path: 'agendar', component: AgendamentoComponent},
  { path: 'agendamento', component: ListaragendamentoComponent},
  { path: '', pathMatch:'full',redirectTo:'inicio'},
  
 
];





@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    PetsComponent,
    RelatoriosComponent,
    InicioComponent,
    ReplacePipe,
    AgendamentoComponent,
    ListaragendamentoComponent,
    
    

    
    
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
    CommonModule,
    
    
    
    
    
  ],
  providers: [PetshopService, provideAnimations(),{ provide: LOCALE_ID, useValue: 'pt-BR' }],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {
  constructor() {
    registerLocaleData(pt);
 }
}



