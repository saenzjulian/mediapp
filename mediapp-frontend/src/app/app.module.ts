import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './material/material.module';
import { PacienteComponent } from './_pages/paciente/paciente.component';
import { MedicoComponent } from './_pages/medico/medico.component';
import { PacienteEdicionComponent } from './_pages/paciente/paciente-edicion/paciente-edicion.component';  
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MedicoDialogoComponent } from './_pages/medico/medico-dialogo/medico-dialogo.component';
import { ExamenComponent } from './_pages/examen/examen.component';
import { ExamenEdicionComponent } from './_pages/examen/examen-edicion/examen-edicion.component';
import { EspecialidadComponent } from './_pages/especialidad/especialidad.component';
import { EspecialidadEdicionComponent } from './_pages/especialidad/especialidad-edicion/especialidad-edicion.component';
import { ConsultaComponent } from './_pages/consulta/consulta.component';
import { ConsultaEspecialComponent } from './_pages/consulta-especial/consulta-especial.component';
import { WizardComponent } from './_pages/wizard/wizard.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BuscarComponent } from './_pages/buscar/buscar.component';
import { BuscarDialogoComponent } from './_pages/buscar/buscar-dialogo/buscar-dialogo.component';
import { ReporteComponent } from './_pages/reporte/reporte.component'; 



@NgModule({
  declarations: [
    AppComponent,
    PacienteComponent,
    MedicoComponent,
    PacienteEdicionComponent,
    MedicoDialogoComponent,
    ExamenComponent,
    ExamenEdicionComponent,
    EspecialidadComponent,
    EspecialidadEdicionComponent,
    ConsultaComponent,
    ConsultaEspecialComponent,
    WizardComponent,
    BuscarComponent,
    BuscarDialogoComponent,
    ReporteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
