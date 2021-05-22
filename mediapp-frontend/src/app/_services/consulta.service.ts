import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment'; 
import { ConsultaListaExamenDTO } from '../_dto/consultaListaExamenDTO';
 
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
    private htth: HttpClient
  ) { }

  registrarTransaccion(consultaListaExamenDTO: ConsultaListaExamenDTO){
    return this.htth.post(this.url, consultaListaExamenDTO);
  }
}
