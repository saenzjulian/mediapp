import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicoComponent } from './_pages/medico/medico.component';
import { PacienteComponent } from './_pages/paciente/paciente.component';

const routes: Routes = [
  {path: 'medico', component: MedicoComponent},
  {path: 'paciente', component: PacienteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
