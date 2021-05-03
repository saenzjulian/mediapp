import { Component, OnInit } from '@angular/core';
import { Paciente } from 'src/app/_models/paciente';
import { PacienteService } from 'src/app/_services/paciente.service';

@Component({
  selector: 'app-paciente',
  templateUrl: './paciente.component.html',
  styleUrls: ['./paciente.component.css']
})
export class PacienteComponent implements OnInit {

  pacientes: Paciente[];

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
      this.pacientes = data;
    });
  }

}
