import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Paciente } from '../_models/paciente';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {

  private url : string = `${environment.HOST}/pacientes`

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Paciente[]>(this.url);
  }

  listarById(id: number){
    return this.http.get<Paciente>(`${this.url}/${id}`);
  }


}
