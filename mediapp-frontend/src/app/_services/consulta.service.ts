import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment'; 
import { ConsultaListaExamenDTO } from '../_dto/consultaListaExamenDTO';
import { ConsultaResumenDTO } from '../_dto/ConsultaResumenDTO';
import { FiltroConsultaDTO } from '../_dto/filtroConsultaDTO';
import { Consulta } from '../_models/consulta';
 
@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  /**
   * No puedo extender del generic porque solo apunta a una entidad en particular
   * y como lo que quiero es insertar en más de una tabla entonces necesito 
   * algo del tipo transaccional
   */

  private url: string = `${environment.HOST}/consultas`

  constructor(
    private http: HttpClient
  ) { }

  registrarTransaccion(consultaListaExamenDTO: ConsultaListaExamenDTO){
    return this.http.post(this.url, consultaListaExamenDTO);
  }

  buscarOtros(filtroConsulta: FiltroConsultaDTO) {
    return this.http.post<Consulta[]>(`${this.url}/buscar/otros`, filtroConsulta);
  }

  buscarFecha(fecha: string) {
    return this.http.get<Consulta[]>(`${this.url}/buscar?fecha=${fecha}`);
  }

  listarExamenPorConsulta(idConsulta: number) {
    return this.http.get<ConsultaListaExamenDTO[]>(`${environment.HOST}/consultaexamenes/${idConsulta}`);
  }

  listarResumen() {
    return this.http.get<ConsultaResumenDTO[]>(`${this.url}/listarResumen`);
  }
}
