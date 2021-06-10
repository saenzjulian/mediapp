import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 
import { BuscarComponent } from './buscar/buscar.component';
import { ConsultaEspecialComponent } from './consulta-especial/consulta-especial.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { EspecialidadEdicionComponent } from './especialidad/especialidad-edicion/especialidad-edicion.component';
import { EspecialidadComponent } from './especialidad/especialidad.component';
import { ExamenComponent } from './examen/examen.component';
import { ExamenEdicionComponent } from './examen/examen-edicion/examen-edicion.component'; 
import { MedicoComponent } from './medico/medico.component' 
import { PacienteEdicionComponent } from './paciente/paciente-edicion/paciente-edicion.component';
import { PacienteComponent } from './paciente/paciente.component';
import { ReporteComponent } from './reporte/reporte.component';
import { WizardComponent } from './wizard/wizard.component';
import { InicioComponent } from './inicio/inicio.component'

export const routes: Routes = [
    { path: 'inicio', component: InicioComponent },
    {
        path: 'paciente', component: PacienteComponent, children: [
            { path: 'nuevo', component: PacienteEdicionComponent },
            { path: 'edicion/:id', component: PacienteEdicionComponent }
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
            { path: 'edicion/:id', component: EspecialidadEdicionComponent }
        ] 
    },
    { path: 'medico', component: MedicoComponent },
    { path: 'consulta', component: ConsultaComponent },
    { path: 'consulta-wizard', component: WizardComponent },
    { path: 'consulta-especial', component: ConsultaEspecialComponent },
    { path: 'buscar', component: BuscarComponent },
    { path: 'reporte', component: ReporteComponent }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PagesRoutingModule { }