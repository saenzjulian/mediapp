import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicoComponent } from './_pages/medico/medico.component';
import { PacienteEdicionComponent } from './_pages/paciente/paciente-edicion/paciente-edicion.component';
import { PacienteComponent } from './_pages/paciente/paciente.component';

const routes: Routes = [
  { path: 'medico', component: MedicoComponent },
  {
    path: 'paciente', component: PacienteComponent, children: [
      { path: 'nuevo', component: PacienteEdicionComponent },
      { path: 'edicion/:id', component: PacienteEdicionComponent } // Los : quiere decir parte mutable
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
