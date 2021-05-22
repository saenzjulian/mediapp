import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConsultaEspecialComponent } from './_pages/consulta-especial/consulta-especial.component';
import { ConsultaComponent } from './_pages/consulta/consulta.component';
import { EspecialidadEdicionComponent } from './_pages/especialidad/especialidad-edicion/especialidad-edicion.component';
import { EspecialidadComponent } from './_pages/especialidad/especialidad.component';
import { ExamenEdicionComponent } from './_pages/examen/examen-edicion/examen-edicion.component';
import { ExamenComponent } from './_pages/examen/examen.component';
import { MedicoComponent } from './_pages/medico/medico.component';
import { PacienteEdicionComponent } from './_pages/paciente/paciente-edicion/paciente-edicion.component';
import { PacienteComponent } from './_pages/paciente/paciente.component';

const routes: Routes = [
  { path: 'medico', component: MedicoComponent },
  {
    path: 'paciente', component: PacienteComponent, children: [
      { path: 'nuevo', component: PacienteEdicionComponent },
      { path: 'edicion/:id', component: PacienteEdicionComponent } // Los :id quiere decir parte mutable
    ]
  },
  {
    path: 'examen', component: ExamenComponent, children: [
      { path: 'nuevo', component: ExamenEdicionComponent },
      { path: 'edicion/:id', component: ExamenEdicionComponent } // Los :id quiere decir parte mutable
    ]
  },
  {
    path: 'especialidad', component: EspecialidadComponent, children: [
      { path: 'nuevo', component: EspecialidadEdicionComponent },
      { path: 'edicion/:id', component: EspecialidadEdicionComponent } // Los :id quiere decir parte mutable
    ]
  },
  { path: 'consulta', component: ConsultaComponent },
  { path: 'consulta-especial', component: ConsultaEspecialComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
