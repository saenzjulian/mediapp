import { DetalleConsulta } from "./detalleConsulta";
import { Especialidad } from "./especialidad";
import { Medico } from "./medico";
import { Paciente } from "./paciente"

export class Consulta {
    idConsulta: number;
    paciente: Paciente;
    medico: Medico;
    especialidad: Especialidad;
    fecha: string; // para enviar el formato que recibe el backend dd/mm/yyyyThh:mm:ssZ
    numeroConsultorio: string;
    detalleConsulta: DetalleConsulta[];

}