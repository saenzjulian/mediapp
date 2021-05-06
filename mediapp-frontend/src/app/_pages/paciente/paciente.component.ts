import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
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
  dataSource: MatTableDataSource<Paciente>
  displayedColumns: string [] = ['idPaciente', 'nombres', 'apellidos', 'acciones']

  // Dependency injection
  constructor(private pacienteService: PacienteService) { }

  /**
   * ngOnInit se ejecuta inmediatamente después del constructor
   * no ejecuto la sentencia .listar dentro del constructor
   * proque se corre el riesgo de que me de un null, por eso no se hace en ngOnInit
   */
  ngOnInit(): void {
    // .subscribe hace parte de la programación reactiva porque en codigo es asincrono
    this.pacienteService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  filtrar(valor: string){
    this.dataSource.filter = valor.trim().toLowerCase();

  }

}
