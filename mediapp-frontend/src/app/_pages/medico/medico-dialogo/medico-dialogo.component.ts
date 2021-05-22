import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Medico } from 'src/app/_models/medico';
import { MedicoService } from 'src/app/_services/medico.service';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-medico-dialogo',
  templateUrl: './medico-dialogo.component.html',
  styleUrls: ['./medico-dialogo.component.css']
})
export class MedicoDialogoComponent implements OnInit {

  medico: Medico;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: Medico,
    private dialogRef: MatDialogRef<MedicoDialogoComponent>,
    private medicoService: MedicoService
  ) { }

  ngOnInit(): void {
    // no puedo pasar la misma variable por referencia porque sino va a ser mutable - referencia de memoria
    // this.medico = this.data;
    /**
     * Puedo hacerlo como lo siguiente, pero, que pasa cuando son 100 atributos? inutil
     * this.medico = new Medico();
     * this.medico.idMedico = this.data.idMedico;
     * this.medico.nombres = this.data.nombres;
     * this.medico.apellidos = this.data.apellidos ;
     * this.medico.cmp = this.data.cmp;
     * this.medico.fotoUrl = this.data.fotoUrl; 
     */

    this.medico = { ...this.data }
  }

  operar() {
    if (this.medico != null && this.medico.idMedico > 0) {
      // Update
      this.medicoService.modificar(this.medico).pipe(switchMap(() => {
        return this.medicoService.listar()
      })).subscribe(data => {
        this.medicoService.setMedicoCambio(data);
        this.medicoService.setMensajeCambio("Medico Modificado");
      });
    } else {
      // Create 
      this.medicoService.registrar(this.medico).pipe(switchMap(() => {
        return this.medicoService.listar()
      })).subscribe(data => {
        this.medicoService.setMedicoCambio(data);
        this.medicoService.setMensajeCambio("Medico Registrado");
      })

    }
    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
