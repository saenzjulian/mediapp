import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Paciente } from '../_models/paciente';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {

  pacienteCambio: Subject<Paciente[]> = new Subject<Paciente[]>(); // Variable para reflejar los cambios/actualizaciones en el otro componente
  mensajeCambio: Subject<string> = new Subject<string>();
  private url : string = `${environment.HOST}/pacientes`

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Paciente[]>(this.url);
  }

  listarById(id: number){
    return this.http.get<Paciente>(`${this.url}/${id}`);
  }

  registrar(paciente: Paciente){
    return this.http.post(this.url, paciente)
  }

  modificar(paciente: Paciente){
    return this.http.put(this.url, paciente)
  }

  eliminar(id: number){
    return this.http.delete(`${this.url}/${id}`);
  }



}
