import { Consulta } from "../_models/consulta";
import { Examen } from "../_models/examen";

export class ConsultaListaExamenDTO{
    consulta: Consulta;
    listExamen: Examen[]
}