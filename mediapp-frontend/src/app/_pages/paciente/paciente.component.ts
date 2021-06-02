import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Paciente } from 'src/app/_models/paciente';
import { PacienteService } from 'src/app/_services/paciente.service';

@Component({
  selector: 'app-paciente',
  templateUrl: './paciente.component.html',
  styleUrls: ['./paciente.component.css']
})
export class PacienteComponent implements OnInit {
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource: MatTableDataSource<Paciente>;
  displayedColumns: string[] = ['idPaciente', 'nombres', 'apellidos', 'acciones'];
  cantidad: number = 0;

  // Dependency injection
  constructor(
    private pacienteService: PacienteService,
    private snackBar: MatSnackBar
    ) { }

  /**
   * ngOnInit se ejecuta inmediatamente después del constructor
   * no ejecuto la sentencia .listar dentro del constructor
   * proque se corre el riesgo de que me de un null, por eso no se hace en ngOnInit
   */
  ngOnInit(): void {
    // .subscribe hace parte de la programación reactiva porque en codigo es asincrono  
    /*
    this.pacienteService.listar().subscribe(data => {
      this.crearTabla(data);
    });
    */

    this.pacienteService.listarPageable(0, 10).subscribe(data => {
      this.cantidad = data.totalElements;
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.sort;
    });

    /**
     * Esta variable reactiva [pcienteCambio] UNICA y EXCLUSIVAMENTE
     * va a reaccionar cuando en algun lado del proyecto le hagan un .next
     */
    this.pacienteService.pacienteCambio.subscribe(data => {
      this.crearTabla(data);
    })

    /**
     * Esta es la implementación de otra variable reactiva
     */
    this.pacienteService.mensajeCambio.subscribe(data =>{
      this.snackBar.open(data, 'AVISO', {duration: 4000});
    });

  }

  filtrar(valor: string){
    this.dataSource.filter = valor.trim().toLowerCase();
  }

  crearTabla(data: Paciente[]){
    this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
  }

  eliminar(id: number){
    this.pacienteService.eliminar(id).subscribe(() => {
      this.pacienteService.listar().subscribe(data => {
        this.crearTabla(data);
        /** 
         * En este punto tambien puedo usar la variable reactiva pacienteService.pacienteCambio
         * porque no interesa en que componente esté, si doy un .next ella va a reaccionar  
         */
         this.pacienteService.mensajeCambio.next("Paciente Eliminado")
      });   
    });
  }

  mostrarMas(e: any){
    this.pacienteService.listarPageable(e.pageIndex, e.pageSize).subscribe(data => {
      this.cantidad = data.totalElements;
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.sort;
    });
  }

}
