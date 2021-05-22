import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Paciente } from '../_models/paciente';
import { Subject } from 'rxjs';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class PacienteService  extends GenericService<Paciente>{

  pacienteCambio: Subject<Paciente[]> = new Subject<Paciente[]>(); // Variable para reflejar los cambios/actualizaciones en el otro componente
  mensajeCambio: Subject<string> = new Subject<string>();

  /**
  private url : string = `${environment.HOST}/pacientes`
  constructor(private http: HttpClient) { }
  */

  constructor(protected http: HttpClient) {
    /**
     * super invoca al constructor padre de la herencia -> GenericService
     * es decir, que en sus parámetros va a pedir los que la clase padre necesita
     * -  una instancia de http             ->  http
     * -  url de conexión al servicio rest  ->  url
     * 
     * para así usar los metodos listar, listarById, resgistrar, modificar, eliminar
     */     
    super(
      http,
      `${environment.HOST}/pacientes`);
  } 
  
}
