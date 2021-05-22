import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { switchMap } from 'rxjs/operators';
import { Medico } from 'src/app/_models/medico';
import { MedicoService } from 'src/app/_services/medico.service';
import { MedicoDialogoComponent } from './medico-dialogo/medico-dialogo.component';

@Component({
  selector: 'app-medico',
  templateUrl: './medico.component.html',
  styleUrls: ['./medico.component.css']
})
export class MedicoComponent implements OnInit {
  /**
   * Invocar al servicio
   * Traer la lista
   * Poblarlo en la variable 
   */

  displayedColumns = ['idmedico', 'nombres', 'apellidos', 'cmp', 'acciones'];
  dataSource: MatTableDataSource<Medico>;

  // @ViewChild me permite tener una referencia de una etiqueta que tengo en el html
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private medicoService: MedicoService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.medicoService.getMedicoCambio().subscribe(data => {
      this.crearTabla(data);
    });

    this.medicoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', { duration: 4000 });
    });

    this.medicoService.listar().subscribe(data => {
      this.crearTabla(data);
    }); 
  }

  crearTabla(data: Medico[]) {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  filtrar(valor: string) {
    this.dataSource.filter = valor.trim().toLowerCase();
  }

  abrirDialogo(medico?: Medico) { 
    this.dialog.open(MedicoDialogoComponent, {
      width: '250px',
      data: medico
    })
  }

  eliminar(medico: Medico) { 
    /**
     * No suscribirse hasta no estar seguro de la información que necesito
     * .pipe me gestiona las suscripciones con el operador switchMap
     * 
     * como el método eliminar no retornó nada por eso uso () y continuo
     * como el método listar si me devuelve nada y es la data que yo necesito ahí si me suscribo
     * 
     */
    this.medicoService.eliminar(medico.idMedico).pipe(switchMap(() => {
      return this.medicoService.listar()
    })).subscribe(data => {
      this.medicoService.setMensajeCambio("Medico Eliminado")
      this.crearTabla(data)
    }) 
  }

}
